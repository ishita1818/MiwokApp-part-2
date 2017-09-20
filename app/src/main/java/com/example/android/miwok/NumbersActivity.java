package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
protected AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int FocusChange) {

            if(FocusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||FocusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaplayer.pause();
                mMediaplayer.seekTo(0);

            }
            else if(FocusChange==AudioManager.AUDIOFOCUS_GAIN){
                mMediaplayer.start();
            }
            else if(FocusChange==AudioManager.AUDIOFOCUS_LOSS){
                releseMediaPlayer();
            }
        }
    };

    protected MediaPlayer mMediaplayer;
    ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        words = new ArrayList<Word>();

        words.add(new Word("one", "lutti", R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two", "otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na’aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);

        ListView listView = (ListView)findViewById(R.id.list);


        listView.setAdapter(adapter);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                releseMediaPlayer();
               int result= mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                       AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result== AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaplayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getAudioResourceId());
                    mMediaplayer.start();
                    mMediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaplayer) {
                            releseMediaPlayer();
                        }
                    });
                }
            }
        });



    }
    private void releseMediaPlayer(){
        if(mMediaplayer!=null){
            mMediaplayer.release();

        mMediaplayer=null;
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);}
    }

    @Override
    protected void onStop() {
        super.onStop();
        releseMediaPlayer();
    }
}
