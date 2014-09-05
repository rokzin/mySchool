package com.rokzin.myschool.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Card extends ViewFlipper {

	private String question;
	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	private String answer;
	private boolean mode;
	private Context context;
	public Card(Context context, String question, String answer) {
		super(context);
		this.question = question;
		this.answer = answer;
		this.context = context;
		addView(createQuestionCard());
	}
	
	public void toggle(){
		removeAllViews();
		if(mode){
			addView(createQuestionCard());
		}
		else{
			addView(createAnswerCard());
		}
	}

	public Card(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private  RelativeLayout createQuestionCard() {
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
		tv.setText(question);
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
	private  RelativeLayout createAnswerCard() {
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
