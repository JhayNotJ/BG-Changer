package anytype.with.adjustment;

import android.content.*;
import android.util.*;
import android.widget.*;
import android.preference.*;
import android.graphics.*;
import anytype.with.adjustment.*;

public class JhayImageView extends ImageView
{
	SharedPreferences sp;
	String imgpath,storedpath;
	public static final String OPACITY1 = "opct1";
	
	public JhayImageView(Context ctx, AttributeSet attrs)
	{
		super(ctx, attrs);
		sp = getContext().getSharedPreferences("setback",Context.MODE_PRIVATE);
		if(sp.contains("imagepath")) {
			storedpath=sp.getString("imagepath", "");
			setImageBitmap(BitmapFactory.decodeFile(storedpath));
			setAlpha(sp.getInt(OPACITY1,  255));
			
		}
}
}
