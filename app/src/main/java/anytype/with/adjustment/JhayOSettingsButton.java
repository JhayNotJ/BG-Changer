package anytype.with.adjustment;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.*;
import android.content.*;
import android.view.View.*;
import android.view.*;


public class JhayOSettingsButton extends Button implements OnClickListener
{
	public JhayOSettingsButton(Context ctx, AttributeSet attrs)
	{
		super(ctx, attrs);
		this.init(ctx);

	}
	private void init(Context ctx){
		setOnClickListener(this);
		}

	@Override
	public void onClick(View view)
	{
		
		Intent i = new Intent(getContext(), JhaySettings.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getContext().startActivity(i);
		
		}
		// TODO: Implement this method

	}
