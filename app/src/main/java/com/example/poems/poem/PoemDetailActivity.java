package com.example.poems.poem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.example.poems.DatabaseHelper;
import com.example.poems.R;

import com.example.poems.home.MainActivity;
import com.example.poems.home.RecitingFragment;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

/*
This is the detail page of the poem with the title, author, content, explanation
 */

public class PoemDetailActivity extends AppCompatActivity
        implements View.OnClickListener, PlaybackPreparer, PlayerControlView.VisibilityListener{

    public static final String POEM_ID = "poemId";//id in db
    public static final String P_ID = "pId"; //poem_id in db
    public static final String POEM_INDEX = "poemIndex";
    public static final String POEM_ID_LIST = "poemIdList";
    public static final String POEM_IS_RECITED = "poemIsRecited";
    public static final String GRADE = "grade";
    public static final String PAGE_SOURCE = "pageSource";

    // private Poem poem;
    private Cursor cursor;
//    private SQLiteDatabase db;
    private ArrayList<Integer> poemIdList;
    private int poemId;
    private int pId;
    private int poemTotal;
    private int poemIndex;
    private int grade;
    private boolean poemIsRecited;
    private String pageSource;
    private Button recitedButton;
    private ImageView recitedImage;
    private PlayerView playerView;
    private SimpleExoPlayer player;

    // Saved instance state keys.

    private static final String KEY_TRACK_SELECTOR_PARAMETERS = "track_selector_parameters";
    private static final String KEY_WINDOW = "window";
    private static final String KEY_POSITION = "position";
    private static final String KEY_AUTO_PLAY = "auto_play";

    private LinearLayout debugRootView;
    private Button selectTracksButton;
    private TextView debugTextView;

    private boolean startAutoPlay;
    private int startWindow;
    private long startPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);
        poemId = (Integer) getIntent().getExtras().get(POEM_ID);
        pId = (Integer) getIntent().getExtras().get(P_ID);
//        System.out.println("=======" +  pId);
        getPoemDetailById(poemId);
        poemIndex = (Integer) getIntent().getExtras().get(POEM_INDEX);
        poemIdList = getIntent().getIntegerArrayListExtra(POEM_ID_LIST);
        pageSource = (String) getIntent().getExtras().get(PAGE_SOURCE);

//      hide the previous button for the first poem
        if(poemIndex == 0) {
            Button prevButton = findViewById(R.id.poem_prev);
            prevButton.setVisibility(View.INVISIBLE);
        }
        poemTotal = poemIdList.size();
//        hide the next button for the last poem
        if(poemIndex == poemTotal - 1) {
            Button nextButton = findViewById(R.id.poem_next);
            nextButton.setVisibility(View.INVISIBLE);
        }
//      已经背出来的古诗，隐藏"背出来了"按钮，显示已经背出来图片
        if (poemIsRecited) {
            Button recitedButton = findViewById(R.id.poem_recited);
            recitedButton.setVisibility(View.INVISIBLE);
            ImageView recitedImage = findViewById(R.id.img_recited);
            recitedImage.setVisibility(View.VISIBLE);
        } else {
            recitedButton = findViewById(R.id.poem_recited);
            recitedButton.setVisibility(View.VISIBLE);
            recitedImage = findViewById(R.id.img_recited);
            recitedImage.setVisibility(View.INVISIBLE);
        }

        playerView = findViewById(R.id.player_view);
        playerView.setVisibility(View.INVISIBLE);
        debugRootView = findViewById(R.id.controls_root);
        debugTextView = findViewById(R.id.debug_text_view);
        selectTracksButton = findViewById(R.id.select_tracks_button);
        selectTracksButton.setOnClickListener(this);
        // for poems with audio
        if (1009 == pId) {
            playerView.setVisibility(View.VISIBLE);
            playerView.setControllerVisibilityListener(this);
            Uri mp4VideoUri = Uri.parse("asset:///jingyesi.mp4");
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                    Util.getUserAgent(this, "Poems"));
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(mp4VideoUri);


            player = new SimpleExoPlayer.Builder(this).build();
            playerView.setPlayer(player);
            playerView.setPlaybackPreparer(this);
            // Prepare the player with the source.
            player.prepare(videoSource);
        }
    }

//    获取前一首诗
    public void getPreviousOne(View view){
        int poemNewIndex = poemIndex-1;
        poemId = poemIdList.get(poemNewIndex);
        Intent intent = getIntent();
        intent.putExtra(POEM_ID, poemId);
        intent.putExtra(PoemDetailActivity.POEM_INDEX, poemNewIndex);
        intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
        startActivity(intent);
    }

//    获取后一首诗
    public void getNextOne(View view){
        int poemNewIndex = poemIndex + 1;
        poemId = poemIdList.get(poemNewIndex);
        Intent intent = getIntent();
        intent.putExtra(POEM_ID, poemId);
        intent.putExtra(PoemDetailActivity.POEM_INDEX, poemNewIndex);
        intent.putIntegerArrayListExtra(PoemDetailActivity.POEM_ID_LIST, poemIdList);
        startActivity(intent);
    }

//    加入到已经背出来的古诗列表中
    public void addToRecited(View view){
        new UpdatePoemTask().execute(poemId);
    }

    private void getPoemDetailById(int id) {

        try{
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            cursor = db.query("POEM", new String[]{"TITLE", "AUTHOR", "CONTENT", "DESCRIPTION", "IS_PASS"},"_id = ?", new String[]{Integer.toString(poemId)}, null, null, null);
            if (cursor.moveToFirst()) {
                String title = cursor.getString(0);
                String author = cursor.getString(1);
                String content = cursor.getString(2);
                String description = cursor.getString(3);
                TextView titleView = findViewById(R.id.titleDetail);
                titleView.setText(title);
                TextView authorView = findViewById(R.id.authorDetail);
                authorView.setText(author);
                TextView contentView = findViewById(R.id.contentDetail);
                contentView.setText(content);
                TextView descView = findViewById(R.id.contentDesc);
                descView.setText(description);
                descView.setMovementMethod(new ScrollingMovementMethod());
                poemIsRecited = (cursor.getInt(4) == 1);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class UpdatePoemTask extends AsyncTask<Integer, Void, Boolean> {

        ContentValues poemValues;

        protected void onPreExecute() {
            poemValues = new ContentValues();
            poemValues.put("IS_PASS", true);
            poemValues.put("IS_RECITING", false);
        }

        protected Boolean doInBackground(Integer... poemId) {
            int id = poemId[0];
            SQLiteOpenHelper poemDatabaseHelper = new DatabaseHelper(PoemDetailActivity.this);
            try {
                SQLiteDatabase db = poemDatabaseHelper.getWritableDatabase();
                db.update("Poem", poemValues, "_id = ?", new String[]{Integer.toString(id)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {
            if(!success) {
                Toast toast = Toast.makeText(PoemDetailActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                recitedButton.setVisibility(View.INVISIBLE);
                recitedImage.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
//            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
//            releasePlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        releaseAdsLoader();
    }

    @Override
    public void preparePlayback() {
        player.retry();
    }

    @Override
    public void onVisibilityChange(int visibility) {

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        releasePlayer();
//        releaseAdsLoader();
//        clearStartPosition();
        setIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        updateTrackSelectorParameters();
//        updateStartPosition();
//        outState.putParcelable(KEY_TRACK_SELECTOR_PARAMETERS, trackSelectorParameters);
        outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
        outState.putInt(KEY_WINDOW, startWindow);
        outState.putLong(KEY_POSITION, startPosition);
    }

    private void initializePlayer() {
//        if (player == null) {
//            Intent intent = getIntent();
//
//            mediaSource = createTopLevelMediaSource(intent);
//            if (mediaSource == null) {
//                return;
//            }
//
//            TrackSelection.Factory trackSelectionFactory;
//            String abrAlgorithm = intent.getStringExtra(ABR_ALGORITHM_EXTRA);
//            if (abrAlgorithm == null || ABR_ALGORITHM_DEFAULT.equals(abrAlgorithm)) {
//                trackSelectionFactory = new AdaptiveTrackSelection.Factory();
//            } else if (ABR_ALGORITHM_RANDOM.equals(abrAlgorithm)) {
//                trackSelectionFactory = new RandomTrackSelection.Factory();
//            } else {
//                showToast(R.string.error_unrecognized_abr_algorithm);
//                finish();
//                return;
//            }
//
//            boolean preferExtensionDecoders =
//                    intent.getBooleanExtra(PREFER_EXTENSION_DECODERS_EXTRA, false);
//            RenderersFactory renderersFactory =
//                    ((DemoApplication) getApplication()).buildRenderersFactory(preferExtensionDecoders);
//
//            trackSelector = new DefaultTrackSelector(/* context= */ this, trackSelectionFactory);
//            trackSelector.setParameters(trackSelectorParameters);
//            lastSeenTrackGroupArray = null;
//
//            player =
//                    new SimpleExoPlayer.Builder(/* context= */ this, renderersFactory)
//                            .setTrackSelector(trackSelector)
//                            .build();
//            player.addListener(new PlayerEventListener());
//            player.setPlayWhenReady(startAutoPlay);
//            player.addAnalyticsListener(new EventLogger(trackSelector));
//            playerView.setPlayer(player);
//            playerView.setPlaybackPreparer(this);
//            debugViewHelper = new DebugTextViewHelper(player, debugTextView);
//            debugViewHelper.start();
//            if (adsLoader != null) {
//                adsLoader.setPlayer(player);
//            }
//        }
//        boolean haveStartPosition = startWindow != C.INDEX_UNSET;
//        if (haveStartPosition) {
//            player.seekTo(startWindow, startPosition);
//        }
//        player.prepare(mediaSource, !haveStartPosition, false);
//        updateButtonVisibility();
    }

    public void backToList(View view) {
        switch(pageSource) {
            case "recitedList":
                backToRecitedList(view);
                break;
            case "recitingList":
                backToRecitingList(view);
                break;
            case "searchList":
                backToSearchList(view);
                break;
            default:
                backToPoemList(view);
                break;
        }
    }

    public void backToPoemList(View view) {
        grade = (Integer) getIntent().getExtras().get(GRADE);
        Intent intent = new Intent(PoemDetailActivity.this, PoemListActivity.class);
        intent.putExtra(PoemListActivity.GRADE, grade);
        startActivity(intent);
    }

    public void backToRecitedList(View view) {
        Intent intent = new Intent(PoemDetailActivity.this, MainActivity.class);
        intent.putExtra(PoemListActivity.GRADE, grade);
        startActivity(intent);
    }

    // TODO: NEED TO CHANGE TO BACK TO THE RECITING FRAGMENT INSTEAD OF THE MAIN ACTIVITY
    public void backToRecitingList(View view) {
        Intent intent = new Intent(PoemDetailActivity.this, MainActivity.class);
//        intent.putExtra(PoemListActivity.GRADE, grade);
        startActivity(intent);
    }

    public void backToSearchList(View view) {
        Intent intent = new Intent(PoemDetailActivity.this, MainActivity.class);
//        intent.putExtra(PoemListActivity.GRADE, grade);
        startActivity(intent);
    }
}
