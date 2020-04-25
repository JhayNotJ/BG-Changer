package anytype.with.adjustment;


import android.app.*;
import android.os.*;
public class Main extends Activity 
{


	@Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(abc("main","layout"));
	}
	public int abc(String name,String Type)
	{
		return getBaseContext().getResources().getIdentifier(name,Type,getBaseContext().getPackageName());
	}

}


