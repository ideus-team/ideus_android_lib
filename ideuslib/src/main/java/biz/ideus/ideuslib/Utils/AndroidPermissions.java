package biz.ideus.ideuslib.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class AndroidPermissions {
    public static final int PERMISSIONS_REQUEST_CODE = 1;
    private Activity mContext;
    private String[] mRequiredPermissions;
    private List<String> mPermissionsToRequest = new ArrayList<>();


    public AndroidPermissions(Activity context, String... requiredPermissions) {
        mContext = context;
        mRequiredPermissions = requiredPermissions;
    }

    public boolean checkPermissions() {
        for (String permission : mRequiredPermissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionsToRequest.add(permission);
            }
        }

        if (mPermissionsToRequest.isEmpty()) {
            return true;
        }

        return false;
    }

    public void requestPermissions(int requestCode) {
        String[] request = mPermissionsToRequest.toArray(new String[mPermissionsToRequest.size()]);

        StringBuilder log = new StringBuilder();
        log.append("Requesting permissions:\n");

        for (String permission : request) {
            log.append(permission).append("\n");
        }

        Log.i(getClass().getSimpleName(), log.toString());

        ActivityCompat.requestPermissions(mContext, request, requestCode);
    }


    public boolean areAllRequiredPermissionsGranted(String[] permissions, int[] grantResults) {
        if (permissions == null || permissions.length == 0
                || grantResults == null || grantResults.length == 0) {
            return false;
        }

        LinkedHashMap<String, Integer> perms = new LinkedHashMap<>();

        for (int i = 0; i < permissions.length; i++) {
            if (!perms.containsKey(permissions[i])
                    || (perms.containsKey(permissions[i]) && perms.get(permissions[i]) == PackageManager.PERMISSION_DENIED))
                perms.put(permissions[i], grantResults[i]);
        }

        for (Map.Entry<String, Integer> entry : perms.entrySet()) {
            if (entry.getValue() != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }
}