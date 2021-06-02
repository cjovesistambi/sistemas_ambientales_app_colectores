package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
if (RapidSub.canDelegate("activity_create")) { b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.main parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.main parent;
RemoteObject _firsttime;
RemoteObject _phn = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone");
RemoteObject _result = RemoteObject.createImmutable(false);
RemoteObject _permission = RemoteObject.createImmutable("");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 37;BA.debugLine="Dim phn As Phone";
Debug.ShouldStop(16);
_phn = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone");Debug.locals.put("phn", _phn);
 BA.debugLineNum = 42;BA.debugLine="phn.SetScreenOrientation(0)";
Debug.ShouldStop(512);
_phn.runVoidMethod ("SetScreenOrientation",main.processBA,(Object)(BA.numberCast(int.class, 0)));
 BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"1\")";
Debug.ShouldStop(1024);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("1")),main.mostCurrent.activityBA);
 BA.debugLineNum = 45;BA.debugLine="Dim Result As Boolean = True";
Debug.ShouldStop(4096);
_result = parent.mostCurrent.__c.getField(true,"True");Debug.locals.put("Result", _result);Debug.locals.put("Result", _result);
 BA.debugLineNum = 46;BA.debugLine="If Not(rp.Check(rp.PERMISSION_CAMERA)) Then";
Debug.ShouldStop(8192);
if (true) break;

case 1:
//if
this.state = 4;
if (parent.mostCurrent.__c.runMethod(true,"Not",(Object)(parent._rp.runMethod(true,"Check",(Object)(parent._rp.getField(true,"PERMISSION_CAMERA"))))).<Boolean>get().booleanValue()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 47;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_CAMERA)";
Debug.ShouldStop(16384);
parent._rp.runVoidMethod ("CheckAndRequest",main.processBA,(Object)(parent._rp.getField(true,"PERMISSION_CAMERA")));
 BA.debugLineNum = 48;BA.debugLine="Wait For Activity_PermissionResult (Permission A";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","activity_permissionresult", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_create"), null);
this.state = 10;
return;
case 10:
//C
this.state = 4;
_permission = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Permission", _permission);
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(1));Debug.locals.put("Result", _result);
;
 if (true) break;
;
 BA.debugLineNum = 50;BA.debugLine="If Result Then";
Debug.ShouldStop(131072);

case 4:
//if
this.state = 9;
if (_result.<Boolean>get().booleanValue()) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 BA.debugLineNum = 53;BA.debugLine="nativeMe.InitializeContext";
Debug.ShouldStop(1048576);
parent._nativeme.runVoidMethod ("InitializeContext",main.processBA);
 BA.debugLineNum = 55;BA.debugLine="L3.Text = \"\"";
Debug.ShouldStop(4194304);
parent.mostCurrent._l3.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 56;BA.debugLine="ZX1.LaserColor = Colors.Cyan";
Debug.ShouldStop(8388608);
parent.mostCurrent._zx1.runVoidMethod ("setLaserColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"Cyan"));
 BA.debugLineNum = 57;BA.debugLine="ZX1.MaskColor = Colors.ARGB(150, 0, 0, 200)";
Debug.ShouldStop(16777216);
parent.mostCurrent._zx1.runVoidMethod ("setMaskColor",parent.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 150)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 200))));
 BA.debugLineNum = 58;BA.debugLine="ZX1.BorderColor = Colors.Magenta";
Debug.ShouldStop(33554432);
parent.mostCurrent._zx1.runVoidMethod ("setBorderColor",parent.mostCurrent.__c.getField(false,"Colors").getField(true,"Magenta"));
 BA.debugLineNum = 59;BA.debugLine="ZX1.BorderStrokeWidth = 7";
Debug.ShouldStop(67108864);
parent.mostCurrent._zx1.runVoidMethod ("setBorderStrokeWidth",BA.numberCast(int.class, 7));
 BA.debugLineNum = 60;BA.debugLine="ZX1.BorderLineLength = 100";
Debug.ShouldStop(134217728);
parent.mostCurrent._zx1.runVoidMethod ("setBorderLineLength",BA.numberCast(int.class, 100));
 BA.debugLineNum = 63;BA.debugLine="ZX1.AutoFocus = True";
Debug.ShouldStop(1073741824);
parent.mostCurrent._zx1.runVoidMethod ("setAutoFocus",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 64;BA.debugLine="ZX1.FocusMode = ZX1.FOCUS_MODE_AUTO";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._zx1.runVoidMethod ("setFocusMode",parent.mostCurrent._zx1.getField(true,"FOCUS_MODE_AUTO"));
 BA.debugLineNum = 71;BA.debugLine="ZX1.Visible = False";
Debug.ShouldStop(64);
parent.mostCurrent._zx1.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 if (true) break;

case 8:
//C
this.state = 9;
 BA.debugLineNum = 79;BA.debugLine="ToastMessageShow(\"Permission not granted\", True)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Permission not granted")),(Object)(parent.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 80;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return ;
 if (true) break;

case 9:
//C
this.state = -1;
;
 BA.debugLineNum = 82;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,88);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 88;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 90;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_permissionresult(RemoteObject _permission,RemoteObject _result) throws Exception{
try {
		Debug.PushSubsStack("Activity_PermissionResult (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,92);
if (RapidSub.canDelegate("activity_permissionresult")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_permissionresult", _permission, _result);}
Debug.locals.put("Permission", _permission);
Debug.locals.put("Result", _result);
 BA.debugLineNum = 92;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 93;BA.debugLine="Log(Permission)";
Debug.ShouldStop(268435456);
main.mostCurrent.__c.runVoidMethod ("LogImpl","0917505",_permission,0);
 BA.debugLineNum = 95;BA.debugLine="Select Case Permission";
Debug.ShouldStop(1073741824);
switch (BA.switchObjectToInt(_permission,main._rp.getField(true,"PERMISSION_CAMERA"))) {
case 0: {
 BA.debugLineNum = 97;BA.debugLine="If Result=True Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",_result,main.mostCurrent.__c.getField(true,"True"))) { 
 };
 break; }
}
;
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,84);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 84;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 86;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _b2_click() throws Exception{
try {
		Debug.PushSubsStack("B2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,200);
if (RapidSub.canDelegate("b2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","b2_click");}
 BA.debugLineNum = 200;BA.debugLine="Sub B2_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 201;BA.debugLine="ZX1.TurnFlashOff";
Debug.ShouldStop(256);
main.mostCurrent._zx1.runVoidMethod ("TurnFlashOff");
 BA.debugLineNum = 209;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,103);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 103;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 104;BA.debugLine="ZX1.Color=Colors.ARGB(0, 0, 0, 0)";
Debug.ShouldStop(128);
main.mostCurrent._zx1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0))));
 BA.debugLineNum = 105;BA.debugLine="ZX1.Visible = True";
Debug.ShouldStop(256);
main.mostCurrent._zx1.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 106;BA.debugLine="ZX1.startScanner";
Debug.ShouldStop(512);
main.mostCurrent._zx1.runVoidMethod ("startScanner");
 BA.debugLineNum = 109;BA.debugLine="scanStarted = True";
Debug.ShouldStop(4096);
main._scanstarted = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 110;BA.debugLine="Log(ZX1.isFlashOn)";
Debug.ShouldStop(8192);
main.mostCurrent.__c.runVoidMethod ("LogImpl","0327687",BA.ObjectToString(main.mostCurrent._zx1.runMethod(true,"isFlashOn")),0);
 BA.debugLineNum = 111;BA.debugLine="ZX1.TurnFlashOn";
Debug.ShouldStop(16384);
main.mostCurrent._zx1.runVoidMethod ("TurnFlashOn");
 BA.debugLineNum = 115;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Private Button1 As Button";
main.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private Panel1 As Panel";
main.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private L1 As Label";
main.mostCurrent._l1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private ZX1 As ZxingBarcodeScanner";
main.mostCurrent._zx1 = RemoteObject.createNew ("zxingbarcodescannerwrapper.zxingBarcodeScannerWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Dim flag As Int = 0";
main._flag = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 30;BA.debugLine="Dim runningavg As Int = 127";
main._runningavg = BA.numberCast(int.class, 127);
 //BA.debugLineNum = 31;BA.debugLine="Private L3 As Label";
main.mostCurrent._l3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private Button2 As Button";
main.mostCurrent._button2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Dim scanStarted As Boolean = False";
main._scanstarted = main.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private nativeMe As JavaObject";
main._nativeme = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
 //BA.debugLineNum = 17;BA.debugLine="Dim rp As RuntimePermissions";
main._rp = RemoteObject.createNew ("anywheresoftware.b4a.objects.RuntimePermissions");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _zx1_brightness_changed(RemoteObject _bright) throws Exception{
try {
		Debug.PushSubsStack("zx1_brightness_changed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,116);
if (RapidSub.canDelegate("zx1_brightness_changed")) { return b4a.example.main.remoteMe.runUserSub(false, "main","zx1_brightness_changed", _bright);}
Debug.locals.put("bright", _bright);
 BA.debugLineNum = 116;BA.debugLine="Sub zx1_brightness_changed (bright As Int )";
Debug.ShouldStop(524288);
 BA.debugLineNum = 118;BA.debugLine="flag = flag + 1";
Debug.ShouldStop(2097152);
main._flag = RemoteObject.solve(new RemoteObject[] {main._flag,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 119;BA.debugLine="L1.Text = \"Brigtness = \" & bright";
Debug.ShouldStop(4194304);
main.mostCurrent._l1.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Brigtness = "),_bright)));
 BA.debugLineNum = 120;BA.debugLine="If flag = 30 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._flag,BA.numberCast(double.class, 30))) { 
 BA.debugLineNum = 121;BA.debugLine="flag = 0";
Debug.ShouldStop(16777216);
main._flag = BA.numberCast(int.class, 0);
 BA.debugLineNum = 122;BA.debugLine="If bright < 150 Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("<",_bright,BA.numberCast(double.class, 150))) { 
 BA.debugLineNum = 123;BA.debugLine="runningavg = (runningavg + bright) / 2";
Debug.ShouldStop(67108864);
main._runningavg = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main._runningavg,_bright}, "+",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0));
 BA.debugLineNum = 125;BA.debugLine="If runningavg < 70 Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("<",main._runningavg,BA.numberCast(double.class, 70))) { 
 BA.debugLineNum = 126;BA.debugLine="If Not(ZX1.isFlashOn) Then ZX1.TurnFlashOn";
Debug.ShouldStop(536870912);
if (main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._zx1.runMethod(true,"isFlashOn"))).<Boolean>get().booleanValue()) { 
main.mostCurrent._zx1.runVoidMethod ("TurnFlashOn");};
 }else 
{ BA.debugLineNum = 127;BA.debugLine="Else if runningavg > 115 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(">",main._runningavg,BA.numberCast(double.class, 115))) { 
 BA.debugLineNum = 128;BA.debugLine="If ZX1.isFlashOn Then ZX1.TurnFlashOff";
Debug.ShouldStop(-2147483648);
if (main.mostCurrent._zx1.runMethod(true,"isFlashOn").<Boolean>get().booleanValue()) { 
main.mostCurrent._zx1.runVoidMethod ("TurnFlashOff");};
 }}
;
 };
 };
 BA.debugLineNum = 132;BA.debugLine="L3.Text = \"isFlashOn = \" & ZX1.isFlashOn";
Debug.ShouldStop(8);
main.mostCurrent._l3.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("isFlashOn = "),main.mostCurrent._zx1.runMethod(true,"isFlashOn"))));
 BA.debugLineNum = 133;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}