package anytype.with.adjustment;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.database.*;
import android.util.*;
import android.provider.*;
import android.view.*;
import android.content.pm.*;
import android.speech.tts.TextToSpeech;
import java.util.*;
import android.speech.tts.*;
import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class JhaySettings extends Activity implements TextToSpeech.OnInitListener{
    private int STORAGE_PERMISSION_CODE = 1;
	
	String mFilePath;
    private static int RESULT_LOAD_IMG = 1;
    String imgpath,storedpath;
    SharedPreferences sp;
    ImageView myImage, devimg;
	SeekBar seekbar;
	TextView opctyCount, jhay,jhaydev;

	public static final String OPACITY = "opcty";
	public static final String OPACITY1 = "opct1";
	private TextToSpeech tts; 
	private final String JhayNotJ = (new String(new byte[]{74,104,97,121,78,111,116,74}));
	private final String jhayToast =( new String(new byte[]{66,71,32,67,104,97,110,103,101,114,32,98,121,32,65,100,109,105,110,32,74,104,97,121,10,74,79,72,78,32,74,69,82,69,77,89,32,65,78,84,73,71,85,79}));
	String ToastInfo = "BG Changer by Admin Jhay"+ System.getProperty("line.separator")+"JOHN JEREMY ANTIGUO";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		LayoutInflater layoutInflater = getLayoutInflater(); 
		
		View toastLayout = layoutInflater.inflate(abc("bg_devinfo","layout"),(ViewGroup) findViewById(abc("customView","id"))); 
		devimg = (ImageView)toastLayout.findViewById(abc("jhayImg","id"));
		jhaydev = (TextView) toastLayout.findViewById(abc("jhayDevInfo","id"));
		
		Toast toast = Toast.makeText(JhaySettings.this,"Toast:Gravity.BOTTOM",Toast.LENGTH_LONG); 
		toast.setGravity(Gravity.BOTTOM,50,50); 
		toast.setView(toastLayout); 
		devimg.setImageResource(abc("ic_launcher","drawable"));
		devimg.setBackgroundResource(abc("jhay_bgbg","drawable"));
		jhaydev.setText(jhayToast);
		jhaydev.setTextSize(10);
		toastLayout.setBackgroundResource(abc("jhay_toastinfo","drawable"));
		toastLayout.setPadding(50, 50, 55, 50);
		
		toast.show(); 
		setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
		
		setContentView(abc("jhay_bgsettings","layout"));
		myImage = (ImageView) findViewById(abc("imgView","id"));
		seekbar = (SeekBar) findViewById(abc("opacitybar","id"));
		opctyCount = (TextView) findViewById(abc("opctyCount","id"));
		jhay = (TextView) findViewById(abc("jhaynotj","id"));
		tts = new TextToSpeech(this, this); 
		
		sp=getSharedPreferences("setback", MODE_PRIVATE);
		seekbar.setProgress(05);
		myImage.setAlpha(50);
		opctyCount.setText("50%");
		jhay.setText(JhayNotJ);
		
		if(sp.contains("imagepath")) {
			storedpath=sp.getString("imagepath", "");
			myImage.setImageBitmap(BitmapFactory.decodeFile(storedpath));
			seekbar.setProgress(sp.getInt(OPACITY, 0));
			myImage.setAlpha(sp.getInt(OPACITY1,255));
			opctyCount.setText(sp.getString("OPACITYCOUNT",""));
		}
		if(jhayToast.equals(ToastInfo)){
		
		}
		else{
			modDetected();
		}
		
		
		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
					SharedPreferences.Editor editor = sp.edit();
					int alpha = seekbar.getProgress();
					myImage.setAlpha(progresValue * 25);
					editor.putString("OPACITYCOUNT", String.valueOf(alpha*10)+"%").commit();
					editor.putInt(OPACITY, progresValue).commit();
					editor.putInt(OPACITY1, seekbar.getProgress() *25).commit();
					opctyCount.setText(String.valueOf(alpha*10)+"%");
					CharSequence text = "Opacity Set to, "+ String.valueOf(alpha*10)+"%"; 

					tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1"); 
					
					}
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {

				}
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {

				}

			});
    }
	public int abc(String name,String Type)
	{
		return getBaseContext().getResources().getIdentifier(name,Type,getBaseContext().getPackageName());
	}
	private void modDetected(){
		AlertDialog.Builder builder1 = new AlertDialog.Builder(JhaySettings.this);
		builder1.setCancelable(false);
		builder1.setIcon(abc("ic_launcher","drawable"));
		builder1.setTitle("ERROR!");
		builder1.setMessage("Modification Detected");

		builder1.setPositiveButton("EXIT", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{

					JhaySettings.this.finish();
				}

			});
		builder1.create().show();
		Handler handler1 = new Handler();
		handler1.postDelayed(new Runnable() {
				public void run() {
					CharSequence text = "Modification Detected!"; 
					tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1");

				}


			}, 200);
	}
	public void apply(View v){
		PackageManager packageManager = getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
		ComponentName componentName = intent.getComponent();
		Intent mainIntent = Intent.makeRestartActivityTask(componentName);
		startActivity(mainIntent);
		Runtime.getRuntime().exit(0);
		
	}
    public void loadImagefromGallery(View view) {
       
		if (ContextCompat.checkSelfPermission(JhaySettings.this,
											  Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
			Intent galleryIntent = new Intent(Intent.ACTION_PICK,
											  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			// Start the Intent
			startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
			pickimage(); 
			
		} else {
			requestStoragePermission();
		}
		// Create intent to Open Image applications like Gallery, Google Photos
		
		
		}
	private void pickimage() { 
	        CharSequence text = "Pick Image"; 
			 
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1"); 
			    } 
	
	private void noImage() { 
		CharSequence text = "You haven't picked Image"; 

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1"); 
	} 
	private void pickimageSuccess() { 
		CharSequence text = "Image, Set, Successful."; 

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1"); 
	} 
	@Override 
		    public void onInit(int status) { 
				
			        if (status == TextToSpeech.SUCCESS) { 
						Set<String> a=new HashSet<>();
						a.add("male");//here you can give male if you want to select male voice.

						Voice v=new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true,a);
						tts.setVoice(v);
						tts.setSpeechRate(0.8f);      
					//set language, you can change to any built in language given we are using US language locale 
				            int result = tts.setVoice(v); 
				            float i = 50; 
				 
				            if (result == TextToSpeech.LANG_MISSING_DATA 
					                    || result == TextToSpeech.LANG_NOT_SUPPORTED) { 
					                Toast.makeText(this, "Language not supported!", Toast.LENGTH_SHORT).show(); 
					            } else { 
					            
					            } 
				 
				        } else { 
				            Toast.makeText(this, "Initilization failed!", Toast.LENGTH_SHORT).show(); 
				        } 
			    } 
		 
	    @Override 
		    public void onDestroy() { 
			        //shutdown TextToSpeech 
			        if (tts != null) { 
				            tts.stop(); 
				            tts.shutdown(); 
				        } 
		        super.onDestroy(); 
			}
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, 
																Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
				.setTitle("Permission needed")
				.setMessage("Permission Required to work properly")
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ActivityCompat.requestPermissions(JhaySettings.this, 
														  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
					}
				})
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.create().show();

        } else {
            ActivityCompat.requestPermissions(this, 
											  new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
												  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// Start the Intent
				startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
				pickimage();  
				} else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
				this.finish();
            }
        }
    }
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
				&& null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.MediaColumns.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
														   filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgpath = cursor.getString(columnIndex);
                Log.d("path", imgpath);
                cursor.close();

				SharedPreferences.Editor edit=sp.edit();
                edit.putString("imagepath",imgpath);
                edit.commit();
				edit.putString("OPACITYCOUNT", "50%").commit();
				edit.putInt(OPACITY, 05).commit();
				edit.putInt(OPACITY1, 50).commit();
				

				Bitmap myBitmap = BitmapFactory.decodeFile(imgpath);

				myImage.setImageBitmap(myBitmap);
				pickimageSuccess();
            }
			else {
                Toast.makeText(this, "You haven't picked Image",
							   Toast.LENGTH_LONG).show();
				noImage();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
				.show();
        }
	}
}
