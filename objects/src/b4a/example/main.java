package b4a.example;

import android.os.Vibrator;
import android.content.Context;
import android.media.ToneGenerator;
import android.media.AudioManager;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4j.object.JavaObject _nativeme = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l1 = null;
public zxingbarcodescannerwrapper.zxingBarcodeScannerWrapper _zx1 = null;
public static int _flag = 0;
public static int _runningavg = 0;
public anywheresoftware.b4a.objects.LabelWrapper _l3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public static boolean _scanstarted = false;
public b4a.example.starter _starter = null;
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
b4a.example.main parent;
boolean _firsttime;
anywheresoftware.b4a.phone.Phone _phn = null;
boolean _result = false;
String _permission = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Dim phn As Phone";
_phn = new anywheresoftware.b4a.phone.Phone();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="phn.SetScreenOrientation(0)";
_phn.SetScreenOrientation(processBA,(int) (0));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="Activity.LoadLayout(\"1\")";
parent.mostCurrent._activity.LoadLayout("1",mostCurrent.activityBA);
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="Dim Result As Boolean = True";
_result = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="If Not(rp.Check(rp.PERMISSION_CAMERA)) Then";
if (true) break;

case 1:
//if
this.state = 4;
if (anywheresoftware.b4a.keywords.Common.Not(parent._rp.Check(parent._rp.PERMISSION_CAMERA))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_CAMERA)";
parent._rp.CheckAndRequest(processBA,parent._rp.PERMISSION_CAMERA);
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "activity_create"), null);
this.state = 10;
return;
case 10:
//C
this.state = 4;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 if (true) break;
;
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="If Result Then";

case 4:
//if
this.state = 9;
if (_result) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="nativeMe.InitializeContext";
parent._nativeme.InitializeContext(processBA);
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="L3.Text = \"\"";
parent.mostCurrent._l3.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="ZX1.LaserColor = Colors.Cyan";
parent.mostCurrent._zx1.setLaserColor(anywheresoftware.b4a.keywords.Common.Colors.Cyan);
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="ZX1.MaskColor = Colors.ARGB(150, 0, 0, 200)";
parent.mostCurrent._zx1.setMaskColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (150),(int) (0),(int) (0),(int) (200)));
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="ZX1.BorderColor = Colors.Magenta";
parent.mostCurrent._zx1.setBorderColor(anywheresoftware.b4a.keywords.Common.Colors.Magenta);
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="ZX1.BorderStrokeWidth = 7";
parent.mostCurrent._zx1.setBorderStrokeWidth((int) (7));
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="ZX1.BorderLineLength = 100";
parent.mostCurrent._zx1.setBorderLineLength((int) (100));
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="ZX1.AutoFocus = True";
parent.mostCurrent._zx1.setAutoFocus(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="ZX1.FocusMode = ZX1.FOCUS_MODE_AUTO";
parent.mostCurrent._zx1.setFocusMode(parent.mostCurrent._zx1.FOCUS_MODE_AUTO);
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="ZX1.Visible = False";
parent.mostCurrent._zx1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 8:
//C
this.state = 9;
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="ToastMessageShow(\"Permission not granted\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Permission not granted"),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 9:
//C
this.state = -1;
;
RDebugUtils.currentLine=131118;
 //BA.debugLineNum = 131118;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_permissionresult", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_permissionresult", new Object[] {_permission,_result}));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Log(Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("0917505",_permission,0);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="Select Case Permission";
switch (BA.switchObjectToInt(_permission,_rp.PERMISSION_CAMERA)) {
case 0: {
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="If Result=True Then";
if (_result==anywheresoftware.b4a.keywords.Common.True) { 
 };
 break; }
}
;
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _b2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "b2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "b2_click", null));}
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub B2_Click";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="ZX1.TurnFlashOff";
mostCurrent._zx1.TurnFlashOff();
RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button1_click", null));}
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub Button1_Click";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="ZX1.Color=Colors.ARGB(0, 0, 0, 0)";
mostCurrent._zx1.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (0),(int) (0),(int) (0)));
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="ZX1.Visible = True";
mostCurrent._zx1.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="ZX1.startScanner";
mostCurrent._zx1.startScanner();
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="scanStarted = True";
_scanstarted = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Log(ZX1.isFlashOn)";
anywheresoftware.b4a.keywords.Common.LogImpl("0327687",BA.ObjectToString(mostCurrent._zx1.isFlashOn()),0);
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="ZX1.TurnFlashOn";
mostCurrent._zx1.TurnFlashOn();
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="End Sub";
return "";
}
public static String  _zx1_brightness_changed(int _bright) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "zx1_brightness_changed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "zx1_brightness_changed", new Object[] {_bright}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub zx1_brightness_changed (bright As Int )";
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="flag = flag + 1";
_flag = (int) (_flag+1);
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="L1.Text = \"Brigtness = \" & bright";
mostCurrent._l1.setText(BA.ObjectToCharSequence("Brigtness = "+BA.NumberToString(_bright)));
RDebugUtils.currentLine=851972;
 //BA.debugLineNum = 851972;BA.debugLine="If flag = 30 Then";
if (_flag==30) { 
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="flag = 0";
_flag = (int) (0);
RDebugUtils.currentLine=851974;
 //BA.debugLineNum = 851974;BA.debugLine="If bright < 150 Then";
if (_bright<150) { 
RDebugUtils.currentLine=851975;
 //BA.debugLineNum = 851975;BA.debugLine="runningavg = (runningavg + bright) / 2";
_runningavg = (int) ((_runningavg+_bright)/(double)2);
RDebugUtils.currentLine=851977;
 //BA.debugLineNum = 851977;BA.debugLine="If runningavg < 70 Then";
if (_runningavg<70) { 
RDebugUtils.currentLine=851978;
 //BA.debugLineNum = 851978;BA.debugLine="If Not(ZX1.isFlashOn) Then ZX1.TurnFlashOn";
if (anywheresoftware.b4a.keywords.Common.Not(mostCurrent._zx1.isFlashOn())) { 
mostCurrent._zx1.TurnFlashOn();};
 }else 
{RDebugUtils.currentLine=851979;
 //BA.debugLineNum = 851979;BA.debugLine="Else if runningavg > 115 Then";
if (_runningavg>115) { 
RDebugUtils.currentLine=851980;
 //BA.debugLineNum = 851980;BA.debugLine="If ZX1.isFlashOn Then ZX1.TurnFlashOff";
if (mostCurrent._zx1.isFlashOn()) { 
mostCurrent._zx1.TurnFlashOff();};
 }}
;
 };
 };
RDebugUtils.currentLine=851984;
 //BA.debugLineNum = 851984;BA.debugLine="L3.Text = \"isFlashOn = \" & ZX1.isFlashOn";
mostCurrent._l3.setText(BA.ObjectToCharSequence("isFlashOn = "+BA.ObjectToString(mostCurrent._zx1.isFlashOn())));
RDebugUtils.currentLine=851985;
 //BA.debugLineNum = 851985;BA.debugLine="End Sub";
return "";
}



/**
*Vibrate with a given pattern. 
*Pass in an array of Longs that are the durations for which to turn on or off the vibrator in milliseconds. 
*The first value indicates the number of milliseconds to wait before turning the vibrator on. The next 
*value indicates the number of milliseconds for which to keep the vibrator on before turning it off. 
*Subsequent values alternate between durations in milliseconds to turn the vibrator off or to turn the vibrator on.
*Parameters:
*pattern ---> an array of longs of times for which to turn the vibrator on or off.
*repeat ---> the index into pattern at which to repeat, or -1 if you don't want to repeat.
*
*You need to add the following to the manifest file of the B4A project:
*AddPermission("android.permission.VIBRATE")
*/
public void vibratePattern(long[] pattern, int repeat) {
    
  Vibrator v = (Vibrator) BA.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
  if (v.hasVibrator()) {
    v.vibrate(pattern, repeat);
  }
}

/**
*Cancel vibration (in case of method vibratePattern being active)
*You need to add the following to the manifest file of the B4A project:
*AddPermission("android.permission.VIBRATE")
*
*/
public void vibrateCancel(int dummy) {
    
  Vibrator v = (Vibrator) BA.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
  if (v.hasVibrator()) {
     v.cancel();
  }
}

/**
*Vibrate continuously for the specified number of milliseconds 
*You need to add the following to the manifest file of the B4A project:
*AddPermission("android.permission.VIBRATE")
*/
public void vibrateOnce(long duration) {
    
  Vibrator v = (Vibrator) BA.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
  if (v.hasVibrator()) {
     v.vibrate(duration);
  }
}


  public void playTone() {
	  final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
	  tg.startTone(ToneGenerator.TONE_PROP_BEEP);
  }	  

}