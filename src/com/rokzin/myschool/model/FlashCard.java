package com.rokzin.myschool.model;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


public class FlashCard {
	
	private String question;
	private String answer;
	private int id;
	
	public FlashCard(String title, String description) {
		this.question = title;
		this.answer = description;
	}
	
	
	public FlashCard(int id,String title, String description) {
		this.id = id;
		this.question = title;
		this.answer = description;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public int getID() {
		return id;
	}


	public void setID(int id) {
		this.id = id;
	}


	public static RelativeLayout createQuestionCard(Context context,String answer) {
		RelativeLayout rl = new RelativeLayout(context);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		rl.setLayoutParams(lp);
		TextView v = new TextView(context);
		v.setId(21);
		v.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		v.setBackgroundColor(Color.parseColor("#E11A31"));
		v.setTextColor(Color.WHITE);
		v.setText("Q");
		v.setTextSize(24);
		TextView tv = new TextView(context);
		tv.setId(22);
		tv.setPadding(20, 5, 10, 5);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setText(answer);
		tv.setTextSize(24);
		tv.setTextColor(Color.parseColor("#E11A31"));
	

		RelativeLayout.LayoutParams vlp = new RelativeLayout.LayoutParams(80, LayoutParams.MATCH_PARENT);
		vlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		vlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
		tvlp.addRule(RelativeLayout.LEFT_OF, v.getId());
		
		rl.addView(v, vlp);
		rl.addView(tv,tvlp);
		
		return rl;
		
	}
	public static RelativeLayout createAnswerCard(Context context,String answer) {
		RelativeLayout rl = new RelativeLayout(context);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		rl.setLayoutParams(lp);
		TextView v = new TextView(context);
		v.setId(21);
		v.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		v.setBackgroundColor(Color.parseColor("#05AC17"));
		v.setTextColor(Color.WHITE);
		v.setText("A");
		v.setTextSize(24);
		TextView tv = new TextView(context);
		tv.setId(22);
		tv.setPadding(20, 5, 10, 5);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setText(answer);
		tv.setTextSize(24);
		tv.setTextColor(Color.parseColor("#05AC17"));
		RelativeLayout.LayoutParams vlp = new RelativeLayout.LayoutParams(80, LayoutParams.MATCH_PARENT);
		vlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		vlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		RelativeLayout.LayoutParams tvlp = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
		tvlp.addRule(RelativeLayout.RIGHT_OF, v.getId());
		
		rl.addView(v, vlp);
		rl.addView(tv,tvlp);
		return rl;
		
	}
}
