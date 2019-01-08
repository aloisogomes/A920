package com.aloisogomes.cordova.plugin;
// The native Toast API
import android.widget.Toast;
import android.util.Log;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.pax.dal.IPrinter;
import com.pax.dal.entity.EFontTypeAscii;
import com.pax.dal.entity.EFontTypeExtCode;
import com.pax.dal.exceptions.PrinterDevException;
import com.pax.neptunelite.api.NeptuneLiteUser;
import com.pax.dal.IDAL;

public class A920 extends CordovaPlugin {
  private static final String DURATION_LONG = "long";
  private static A920 printerTester;
  private IPrinter printer;
  private static IDAL dal;
  private Context context = IS_AT_LEAST_LOLLIPOP ? cordova.getActivity().getWindow().getContext() : cordova.getActivity().getApplicationContext();

  public A920() {
      try {
            dal = NeptuneLiteUser.getInstance().getDal(context);
            printer = dal.getPrinter();
        } catch (Exception e) {
            e.printStackTrace();
        }

  }

 /* public static A920 getInstance() {
        if (printerTester == null) {
            printerTester = new A920();
        }
        return printerTester;
    }*/

    public void init() {
        try {
            printer.init();
            Log.i("init","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("init", e.toString());
        }
    }

    public String getStatus() {
        try {
            int status = printer.getStatus();
            Log.i("getStatus","true");
            return statusCode2Str(status);
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("getStatus", e.toString());
            return "";
        }

    }

    public void fontSet(EFontTypeAscii asciiFontType, EFontTypeExtCode cFontType) {
        try {
            printer.fontSet(asciiFontType, cFontType);
            Log.i("fontSet","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("fontSet", e.toString());
        }

    }

    public void spaceSet(byte wordSpace, byte lineSpace) {
        try {
            printer.spaceSet(wordSpace, lineSpace);
            Log.i("spaceSet","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("spaceSet", e.toString());
        }
    }

    public void printStr(String str, String charset) {
        try {
            printer.printStr(str, charset);
            Log.i("printStr","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("printStr", e.toString());
        }

    }

    public void step(int b) {
        try {
            printer.step(b);
            Log.i("setStep","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("setStep", e.toString());
        }
    }

    public void printBitmap(Bitmap bitmap) {
        try {
            printer.printBitmap(bitmap);
            Log.i("printBitmap","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("printBitmap", e.toString());
        }
    }

    public String start() {
        try {
            int res = printer.start();
            Log.i("start","true");
            return statusCode2Str(res);
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("start", e.toString());
            return "";
        }

    }

    public void leftIndents(short indent) {
        try {
            printer.leftIndent(indent);
            Log.i("leftIndent","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("leftIndent", e.toString());
        }
    }

    public int getDotLine() {
        try {
            int dotLine = printer.getDotLine();
            Log.i("getDotLine","true");
            return dotLine;
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("getDotLine", e.toString());
            return -2;
        }
    }

    public void setGray(int level) {
        try {
            printer.setGray(level);
            Log.i("setGray","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("setGray", e.toString());
        }

    }

    public void setDoubleWidth(boolean isAscDouble, boolean isLocalDouble) {
        try {
            printer.doubleWidth(isAscDouble, isLocalDouble);
            Log.i("doubleWidth","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("doubleWidth", e.toString());
        }
    }

    public void setDoubleHeight(boolean isAscDouble, boolean isLocalDouble) {
        try {
            printer.doubleHeight(isAscDouble, isLocalDouble);
            Log.i("doubleHeight","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("doubleHeight", e.toString());
        }

    }

    public void setInvert(boolean isInvert) {
        try {
            printer.invert(isInvert);
            Log.i("setInvert","true");
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("setInvert", e.toString());
        }

    }

    public String cutPaper(int mode) {
        try {
            printer.cutPaper(mode);
            Log.i("cutPaper","true");
            return "cut paper successful";
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("cutPaper", e.toString());
            return e.toString();
        }
    }

    public String getCutMode() {
        String resultStr = "";
        try {
            int mode = printer.getCutMode();
            Log.i("getCutMode","true");
            switch (mode) {
                case 0:
                    resultStr = "Only support full paper cut";
                    break;
                case 1:
                    resultStr = "Only support partial paper cutting ";
                    break;
                case 2:
                    resultStr = "support partial paper and full paper cutting ";
                    break;
                case -1:
                    resultStr = "No cutting knife,not support";
                    break;
                default:
                    break;
            }
            return resultStr;
        } catch (PrinterDevException e) {
            e.printStackTrace();
            Log.e("getCutMode", e.toString());
            return e.toString();
        }
    }

    public String statusCode2Str(int status) {
        String res = "";
        switch (status) {
            case 0:
                res = "Success ";
                break;
            case 1:
                res = "Printer is busy ";
                break;
            case 2:
                res = "Out of paper ";
                break;
            case 3:
                res = "The format of print data packet error ";
                break;
            case 4:
                res = "Printer malfunctions ";
                break;
            case 8:
                res = "Printer over heats ";
                break;
            case 9:
                res = "Printer voltage is too low";
                break;
            case 240:
                res = "Printing is unfinished ";
                break;
            case 252:
                res = " The printer has not installed font library ";
                break;
            case 254:
                res = "Data package is too long ";
                break;
            default:
                break;
        }
        return res;
    }


  @Override
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("show")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return false;
      }
      String message;
      String duration;

      init();


      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("message");
        duration = options.getString("duration");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return false;
      }
      // Create the toast
      Toast toast = Toast.makeText(cordova.getActivity(), message,
        DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
      // Display toast
      toast.show();
      // Send a positive result to the callbackContext
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
      callbackContext.sendPluginResult(pluginResult);
      return true;
  }


}
