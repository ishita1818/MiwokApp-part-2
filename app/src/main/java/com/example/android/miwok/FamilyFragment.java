package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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

    protected ArrayList<Word> words;
    protected int audio;
    protected MediaPlayer mMediaplayer;

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        words = new ArrayList<Word>();

        words.add(new Word("father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(),words,R.color.category_family);

        ListView listView = (ListView)rootView.findViewById(R.id.list);


        listView.setAdapter(adapter);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                releseMediaPlayer();
                audio= words.get(i).getAudioResourceId();
                int result= mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result== AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaplayer = MediaPlayer.create(getActivity(), words.get(i).getAudioResourceId());
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
        return rootView;
    }
    private void releseMediaPlayer(){
        if(mMediaplayer!=null){
            mMediaplayer.release();

            mMediaplayer=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);}
    }

    @Override
    public void onStop() {
        super.onStop();
        releseMediaPlayer();
    }
}
