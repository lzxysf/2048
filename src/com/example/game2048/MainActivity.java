package com.example.game2048;

import net.youmi.android.AdManager;
import net.youmi.android.normal.spot.SpotManager;
import utils.PrefUtils;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvScore, tv_maxScore;
	private int score = 0;
	private int maxScore = 0;

	private SoundPool sp;// ����һ��SoundPool
	private int music;// ����һ��������load������������suondID

	public MainActivity() {
		mainActivity = this;
	}

	private static MainActivity mainActivity = null;

	public static MainActivity getMainActivity() {
		return mainActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AdManager.getInstance(MainActivity.this).init("31e1fb68e970e6a1",
				"36f7636abed200fe", true, false);
		SpotManager.getInstance(MainActivity.this).setSpotOrientation(
				SpotManager.ORIENTATION_PORTRAIT);
		SpotManager.getInstance(MainActivity.this).setAnimationType(2);
		SpotManager.getInstance(MainActivity.this).showSpotAds(
				MainActivity.this);

		PrefUtils.setBoolean(MainActivity.this, "start", true);
		maxScore = PrefUtils.getInt(MainActivity.this, "maxscore", 0);

		tvScore = (TextView) findViewById(R.id.tvScore);
		tv_maxScore = (TextView) findViewById(R.id.tv_maxScore);

		sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);// ��һ������Ϊͬʱ���������������������ڶ����������ͣ�����Ϊ��������
		music = sp.load(this, R.raw.zuhe, 1); // ����������زķŵ�res/raw���2��������Ϊ��Դ�ļ�����3��Ϊ���ֵ����ȼ�
	}

	public void clearScore() {
		score = 0;
		showScore();
		showMaxScore(maxScore);
	}

	public void showScore() {
		tvScore.setText(score + "");
	}

	public void addScore(int s) {
		score += s;
		showScore();
		sp.play(music, 1, 1, 0, 0, 1);

		if (score > maxScore) {
			maxScore = score;
			PrefUtils.setInt(MainActivity.this, "maxscore", maxScore);
			showMaxScore(maxScore);
		}
	}

	public void showMaxScore(int maxScore) {
		tv_maxScore.setText(maxScore + "");
	}
}
