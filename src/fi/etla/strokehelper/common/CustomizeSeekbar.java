package fi.etla.strokehelper.common;

import fi.etla.strokehelper.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;

public class CustomizeSeekbar extends LinearLayout{

	private SeekBar seekbar;
	private ImageButton minusBtn;
	private ImageButton plusBtn;

	public CustomizeSeekbar(Context context) {
		super(context);
	}

	public CustomizeSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_seekbar, this);
		seekbar = (SeekBar) findViewById(R.id.seekbar);
		minusBtn = (ImageButton) findViewById(R.id.imgbtn_seekbar_minus);
		plusBtn = (ImageButton) findViewById(R.id.imgbtn_seekbar_plus);
	}

	public CustomizeSeekbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public SeekBar getSeekBar(){
		return seekbar;
	}
	public ImageButton getMinusBtn(){
		return minusBtn;
	}
	
	public ImageButton getPlusBtn(){
		return plusBtn;
	}

}
