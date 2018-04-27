package studies.kinkuro.sddar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    public final static int REQ_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionInternet = checkSelfPermission(Manifest.permission.INTERNET);
            int permissionExternalStorage = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionFineLocation= checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

            boolean isGrantedInternet = (permissionInternet == PackageManager.PERMISSION_GRANTED);
            boolean isGrantedExternalStorage = (permissionExternalStorage == PackageManager.PERMISSION_GRANTED);
            boolean isGrantedFineLocation = (permissionFineLocation == PackageManager.PERMISSION_GRANTED);

            if (isGrantedInternet && isGrantedExternalStorage && isGrantedFineLocation){
                //Todo:: MainActivity로 넘어가기
            }else{
                String[] permissions = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, REQ_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQ_PERMISSION:

                if(grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED || grantResults[2] == PackageManager.PERMISSION_DENIED){
                    AlertDialog dialog = new AlertDialog.Builder(this).setMessage("어플리케이션을 사용할 수 없습니다.\n어플리케이션을 종료합니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            IntroActivity.this.finish();
                        }
                    }).create();
                    dialog.setCanceledOnTouchOutside(false);

                    dialog.show();
                }
                break;
        }
    }
}
