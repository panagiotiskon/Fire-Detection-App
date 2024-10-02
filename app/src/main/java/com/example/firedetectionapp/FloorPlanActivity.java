package com.example.firedetectionapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class FloorPlanActivity extends AppCompatActivity {

    private ImageView fireLocationImageView;
    private ImageView userLocationImageView;
    private DrawEscapeRoute drawEscapeRouteView;
    private int selectedRoom;

    private float[][] roomCoordinates = {
            {600, 750},  // Room 1
            {460, 650},  // Room 2
            {450, 530},  // Room 3
            {400, 210},  // Room 4
            {580, 210},  // Room 5
            {750, 210},  // Room 6
            {880, 250},  // Room 7
            {1270, 530}   // Room 8
    };

    private float[][] fireCoordinates = {
            {600, 750}, // sensor 1
            {980, 530},  // sensor 2
            {600, 530},  // sensor 3
            {400, 210},  // sensor 4
            {1300, 530}, // sensor 5
            {1500, 600}, // sensor 6
            {1500, 210}, // sensor 7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        selectedRoom = getIntent().getIntExtra("selectedRoom", -1);

        fireLocationImageView = findViewById(R.id.fire_location);
        userLocationImageView = findViewById(R.id.user_location);
        drawEscapeRouteView = findViewById(R.id.draw_escape_route);

        if (selectedRoom >= 0 && selectedRoom < roomCoordinates.length) {
            setImagePosition(userLocationImageView, roomCoordinates[selectedRoom][0], roomCoordinates[selectedRoom][1]);
            userLocationImageView.setVisibility(View.VISIBLE);
        }

        // Randomly pick a fire location

        Random random = new Random();
        int fireRoom;
        while (true) {
            fireRoom = random.nextInt(fireCoordinates.length);
            if (fireRoom == 0 || fireRoom == 3) {
                if (fireRoom != selectedRoom) break;
            } else break;
        }
        setImagePosition(fireLocationImageView, fireCoordinates[fireRoom][0], fireCoordinates[fireRoom][1]);
        fireLocationImageView.setVisibility(View.VISIBLE);

        int fire = fireRoom;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showFireDetectedAlert(fire);
            }
        }, 2000);

    }

    private void setImagePosition(ImageView imageView, float x, float y) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setLayoutParams(params);
    }

    private void showFireDetectedAlert(int fireRoom) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fire Detected").setMessage("A fire has been detected!Follow the route to exit the building.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // You can now draw the escape route after the dialog
                drawEscape(fireRoom);
            }
        }).show();
    }

    private void drawEscape(int fireRoom) {
        float userX = roomCoordinates[selectedRoom][0];
        float userY = roomCoordinates[selectedRoom][1];
        switch (selectedRoom) {
            case 0:
                if (fireRoom == 5) {
                    drawEscapeRouteView.setPath(userX - 50, userY - 80, userX - 50, 410);
                    drawEscapeRouteView.setPath(userX - 50, 410, 1570, 410);
                    drawEscapeRouteView.setPath(1570, 410, 1570, 670);
                    drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                } else {
                    drawEscapeRouteView.setPath(userX, userY - 80, 1750, 670);
                }
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;

            case 1:
                if (fireRoom == 5) {
                    drawEscapeRouteView.setPath(userX + 60, userY + 20, userX + 100, userY + 20);
                    drawEscapeRouteView.setPath(userX + 100, userY + 20, userX + 100, 410);
                    drawEscapeRouteView.setPath(userX + 100, 410, 1570, 410);
                    drawEscapeRouteView.setPath(1570, 410, 1570, 670);
                    drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                } else {
                    drawEscapeRouteView.setPath(userX+50, userY + 20, 1750, 670);
                }
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 2:
                if (fireRoom == 5) {
                    drawEscapeRouteView.setPath(userX + 50, userY + 70, userX + 100, userY + 70);
                    drawEscapeRouteView.setPath(userX + 100, userY + 70, userX + 100, 410);
                    drawEscapeRouteView.setPath(userX + 100, 410, 1570, 410);
                    drawEscapeRouteView.setPath(1570, 410, 1570, 670);
                    drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                } else {
                    drawEscapeRouteView.setPath(userX + 100, userY + 50, userX + 100, 670);
                    drawEscapeRouteView.setPath(userX + 100, 670, 1750, 670);
                }
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 3:
                if (fireRoom == 5) {
                    drawEscapeRouteView.setPath(userX + 150, userY + 200, userX + 150, 410);
                    drawEscapeRouteView.setPath(userX + 100, 410, 1570, 410);
                    drawEscapeRouteView.setPath(1570, 410, 1570, 670);
                    drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                } else {
                    drawEscapeRouteView.setPath(userX + 150, userY + 200, userX + 150, 670);
                    drawEscapeRouteView.setPath(userX + 150, 670, 1750, 670);
                }
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 4:
                if (fireRoom == 5) {
                    drawEscapeRouteView.setPath(userX - 30, userY + 200, userX - 30, 410);
                    drawEscapeRouteView.setPath(userX - 30, 410, 1570, 410);
                    drawEscapeRouteView.setPath(1570, 410, 1570, 670);
                    drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                } else {
                    drawEscapeRouteView.setPath(userX - 30, userY + 200, userX - 30, 670);
                    drawEscapeRouteView.setPath(userX - 30, 670, 1750, 670);
                }
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 5:
                drawEscapeRouteView.setPath(userX + 50, userY + 150, userX + 50, userY + 200);
                drawEscapeRouteView.setPath(userX + 50, userY + 200, 1570, userY + 200);
                drawEscapeRouteView.setPath(1570, userY + 200, 1570, 670);
                drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 6:
                drawEscapeRouteView.setPath(userX, userY + 160, 1570, userY + 160);
                drawEscapeRouteView.setPath(1570, userY + 160, 1570, 670);
                drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
                break;
            case 7:
                drawEscapeRouteView.setPath(userX + 20, userY - 120, 1570, userY - 120);
                drawEscapeRouteView.setPath(1570, userY - 120, 1570, 670);
                drawEscapeRouteView.setPath(1570, 670, 1750, 670);
                drawEscapeRouteView.setPath(1750, 670, 1750, 750);
                drawEscapeRouteView.setPath(1750, 750, 1650, 750);
                drawEscapeRouteView.drawArrow(1650, 750);
        }
    }
}
