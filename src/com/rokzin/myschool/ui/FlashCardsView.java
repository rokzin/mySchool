package com.rokzin.myschool.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.rokzin.myschool.core.FlashCardAdapter;
import com.rokzin.myschool.model.FlashCard;
import com.rokzin.myschool.model.database.DatabaseHandler;

public class FlashCardsView extends ListView implements android.widget.AdapterView.OnItemClickListener {


	private Context context;
	
	
	public FlashCardsView(Context context) {
		super(context);
		this.context = context;
		
		setDivider(new ColorDrawable(Color.parseColor("#E4E4E4")));
		setPadding(0, 30, 0,30);
		setDividerHeight(30);
		setResults();
		setOnItemClickListener(this);
	}


	public void setResults() {
		DatabaseHandler db = new DatabaseHandler(context);
		this.setAdapter(new FlashCardAdapter(context, db.getAllFlashcards()));
		db.close();
		
	}

	public FlashCardsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onItemClick(AdapterView<?> av, View v, int i, long l) {
		LinearLayout ll = (LinearLayout) av.getChildAt(i);
		ViewFlipper vf = (ViewFlipper) ll.getChildAt(0);
		
		DatabaseHandler db = new DatabaseHandler(context);
		ArrayList<FlashCard> flashCards = db.getAllFlashcards();
		
			vf.removeAllViews();
			vf.addView(FlashCard.createAnswerCard(context,flashCards.get(i).getAnswer()));
		
	}
	

	public void addFlashCardDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Add flashcard");
		final EditText question = new EditText(context);
		question.setHint("Question");
		final EditText answer = new EditText(context);
		answer.setHint("Answer");
		LinearLayout qa = new LinearLayout(context);
		qa.setOrientation(LinearLayout.VERTICAL);
		qa.addView(question);
		qa.addView(answer);
		builder.setView(qa);
		
		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				FlashCard flashCard = new FlashCard(question.getText().toString(),answer.getText().toString());
				DatabaseHandler db = new DatabaseHandler(context);
				db.addFlashcard(flashCard);
				db.close();
				setResults();
				
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
		
	}


}
