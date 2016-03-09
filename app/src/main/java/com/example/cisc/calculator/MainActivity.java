package com.example.cisc.calculator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int DELAY = 100;
    public static String answer_public = "";
    private Interpolator interpolator;
    private LinearLayout slide;
    private LinearLayout linear1;
    private LinearLayout slider;
    private RelativeLayout relativetext;

    private TextView text_process;
    private TextView text_answer;
    float dX;
    float initial_positionx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ANIMACION
        /*ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
        scale.setDuration(3000);
        scale.setInterpolator(new OvershootInterpolator());
        this.findViewById(android.R.id.content).startAnimation(scale);*/
        // ANIMACION

        text_process = (TextView)findViewById(R.id.textView);
        text_answer = (TextView)findViewById(R.id.textView2);

        interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);

        Button number0 = (Button) findViewById(R.id.button0);
        Button number1 = (Button) findViewById(R.id.button);
        Button number2 = (Button) findViewById(R.id.button2);
        Button number3 = (Button) findViewById(R.id.button3);
        Button number4 = (Button) findViewById(R.id.button4);
        Button number5 = (Button) findViewById(R.id.button5);
        Button number6 = (Button) findViewById(R.id.button6);
        Button number7 = (Button) findViewById(R.id.button7);
        Button number8 = (Button) findViewById(R.id.button8);
        Button number9 = (Button) findViewById(R.id.button9);
        Button point = (Button) findViewById(R.id.buttonpoint);
        Button equal = (Button) findViewById(R.id.buttonequal);
        Button delete = (Button) findViewById(R.id.buttondelete);
        Button answer = (Button) findViewById(R.id.buttonanswer);
        Button plus = (Button) findViewById(R.id.buttonplus);
        Button minus = (Button) findViewById(R.id.buttonminus);
        Button times = (Button) findViewById(R.id.buttontimes);
        Button divide = (Button) findViewById(R.id.buttondivide);
        Button open_paren = (Button) findViewById(R.id.buttonopenparen);
        Button close_paren = (Button) findViewById(R.id.buttoncloseparen);
        Button power = (Button) findViewById(R.id.buttonpower);

        slide = (LinearLayout) findViewById(R.id.relative1);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        slider = (LinearLayout) findViewById(R.id.slide);
        relativetext = (RelativeLayout) findViewById(R.id.relativetext);
        initial_positionx = 0;

        slider.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:
                        if (initial_positionx == 0){
                            initial_positionx = v.getX();
                        }
                        dX = v.getX() - event.getRawX();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (v.getResources().getConfiguration().orientation == 1) {
                            if ((event.getRawX() + dX) > v.getPivotX() && (event.getRawX() + dX) <= initial_positionx) {
                                linear1.setAlpha((event.getRawX() + dX) / initial_positionx);
                                v.animate()
                                        .x(event.getRawX() + dX)
                                        .setDuration(0)
                                        .start();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (v.getResources().getConfiguration().orientation == 1) {
                            if ((event.getRawX() + dX) < (v.getPivotX() + 250)) {
                                linear1.animate()
                                        .alpha(v.getPivotX()/initial_positionx)
                                        .setDuration(200)
                                        .start();
                                v.animate()
                                        .x(v.getPivotX())
                                        .setDuration(300)
                                        .start();
                            } else {
                                linear1.animate()
                                        .alpha(1.0f)
                                        .setDuration(200)
                                        .start();
                                v.animate()
                                        .x(initial_positionx)
                                        .setDuration(300)
                                        .start();
                            }
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }

            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                if (getResources().getConfiguration().orientation == 1) {
                    linear1.animate().alpha(1f);
                    slider.animate().translationX(0);
                }
            }
            public void onSwipeLeft() {
                if (getResources().getConfiguration().orientation == 1) {
                    linear1.animate().alpha(0.5f);
                    slider.animate().translationX(-1 * ((3 * slide.getWidth())/5));
                }
            }
            public void onSwipeBottom() {
            }

        });


        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "0");
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "1");
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "2");
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "3");
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process,text_answer, "4");
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "5");
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "6");
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "7");
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "8");
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIf(text_process, text_answer, "9");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temporal = text_process.getText().toString();
                if (!temporal.equals("")) {
                    temporal = temporal.substring(0, temporal.length() - 1);
                    text_process.setText(temporal);
                    if (getResources().getConfiguration().orientation == 1) {
                        scaleDownText();
                    }
                    if (doMathIf(text_process, text_answer) == "")
                    {
                        text_answer.setText("");
                    }
                }
            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                animateButtonsOut();
                int cx = 0;
                int cy = 0;
                int duration = 0;
                if (getResources().getConfiguration().orientation == 1) {
                    cx = relativetext.getRight();
                    cy = relativetext.getBottom();
                    duration = 450;
                } else {
                    cx = (relativetext.getRight() + relativetext.getLeft())/2;
                    cy = relativetext.getBottom();
                    duration = 650;
                }
                int finalRadius = Math.max(relativetext.getWidth(), relativetext.getHeight());

                Animator anim = ViewAnimationUtils.createCircularReveal(relativetext, cx, cy, 0, finalRadius);
                relativetext.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        text_process.setText("");
                        text_answer.setText("");
                        animateButtonsIn();

                        final float[] from = new float[3],
                                to =   new float[3];

                        Color.colorToHSV(getResources().getColor(R.color.colorYellow), from);
                        Color.colorToHSV(getResources().getColor(R.color.colorWhite), to);

                        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
                        anim.setDuration(500);

                        final float[] hsv  = new float[3];                  // transition color
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                // Transition along each axis of HSV (hue, saturation, value)
                                hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                                hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                                hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();

                                relativetext.setBackgroundColor(Color.HSVToColor(hsv));
                            }
                        });

                        anim.start();

                    }
                });
                anim.setDuration(duration);
                anim.start();
                return true;
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_process.getText().toString().matches("(([0-9]+)([-+*/]))*")){
                    checkIf(text_process, text_answer, answer_public);
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumber(text_process, "+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumber(text_process, "-");
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumber(text_process, "*");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumber(text_process, "/");
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text_answer.getText().toString().equals("")) {
                    final Animation in = new AlphaAnimation(0.0f, 1.0f);
                    in.setDuration(300);

                    final Animation out = new AlphaAnimation(1.0f, 0.0f);
                    out.setDuration(300);

                    text_process.startAnimation(out);
                    text_answer.startAnimation(out);

                    out.setAnimationListener(new AnimationListener() {


                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            text_process.setText(text_answer.getText().toString());
                            text_answer.setText("");
                            text_answer.startAnimation(in);
                            text_process.startAnimation(in);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfNumber(text_process, ".");
            }
        });

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void checkIfNumber(TextView text_process, String s) {
        if (text_process.getText().toString().matches("^([0-9]+)([.]([0-9]+))?([-+*\\/]([0-9]+)([.]([0-9]+))?)*")) {
            text_process.append(s);
            scaleText();
        }
    }

    private void checkIf (TextView text, TextView answer, String number) {
        if (text.getText().toString().equals("0")){
            text.setText(number);
            if (getResources().getConfiguration().orientation == 1) {
                scaleText();
            }
        } else {
            text.append(number);
            if (getResources().getConfiguration().orientation == 1) {
                scaleText();
            }
            doMathIf(text, answer);
        }
    }

    private String doMathIf(TextView textp, TextView texta) {
        if (textp.getText().toString().matches("^([0-9]+)([.]([0-9]+))?([-+*\\/]([0-9]+)([.]([0-9]+))?)+")) {
            String postfix =  postfixNotation(textp);
            String result = postfixEvaluation(postfix);
            texta.setText(result);
            answer_public = result;
            return result;
        }
        return "";
    }

    private String postfixEvaluation(String postfix) {
        ArrayList<Float> numbers = new ArrayList<Float>();
        String[] splitit = postfix.split("[,]");
        for (int i = 0; i < splitit.length; i++){
            if (splitit[i].matches("[-+*\\/]")){
                float result = 0;
                switch (splitit[i]){
                    case "+":
                        result = numbers.remove(numbers.size() - 2) + numbers.remove(numbers.size() - 1);
                        break;
                    case "-":
                        result = numbers.remove(numbers.size() - 2) - numbers.remove(numbers.size() - 1);
                        break;
                    case "*":
                        result = numbers.remove(numbers.size() - 2) * numbers.remove(numbers.size() - 1);
                        break;
                    case "/":
                        if (numbers.get(numbers.size() - 1) == 0){
                            return "undefined";
                        }
                        result = numbers.remove(numbers.size() - 2) / numbers.remove(numbers.size() - 1);
                        break;
                }
                numbers.add(result);
            } else if (splitit[i].matches("[0-9]+([.]([0-9]+))?")) {
                numbers.add(Float.parseFloat(splitit[i]));
            }
        }
        return numbers.remove(0)+"";
    }

    private String postfixNotation(TextView textp) {
        ArrayList<String> operands = new ArrayList<String>();
        String process = textp.getText().toString();
        String build = "";
        for (int i = 0; i < process.length(); i++)
        {
            if (process.substring(i,i + 1).matches("[0-9.]")){
                build = build + process.substring(i,i + 1);
            } else {
                build = build + ",";
                if (operands.isEmpty()) {
                    operands.add(process.substring(i,i +1));
                } else {
                    if(operands.get(operands.size() - 1).matches("[+-]") && process.substring(i,i +1).matches("[+-]")){
                        build = build + operands.remove(operands.size() - 1) + ",";
                        operands.add(process.substring(i,i +1));
                    } else if (operands.get(operands.size() - 1).matches("[+-]") && process.substring(i,i +1).matches("[*\\/]")) {
                        operands.add(process.substring(i,i +1));
                    } else if (operands.get(operands.size() - 1).matches("[*\\/]") && process.substring(i,i +1).matches("[*\\/]")) {
                        build = build + operands.remove(operands.size() - 1) + ",";
                        operands.add(process.substring(i,i +1));
                    } else if (operands.get(operands.size() - 1).matches("[*\\/]") && process.substring(i,i +1).matches("[+-]")) {
                        build = build + operands.remove(operands.size() - 1) + ",";
                        operands.add(process.substring(i,i +1));
                    }
                }
            }
        }
        for (int i = (operands.size()-1); i >= 0; i--) {
            build = build + "," + operands.remove(i);
        }
        return build;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void animateButtonsOut() {
        for (int i = 0; i < relativetext.getChildCount(); i++) {
            View child = relativetext.getChildAt(i);
            child.animate()
                    .setStartDelay(i)
                    .setInterpolator(interpolator)
                    .alpha(0)
                    .scaleX(0f)
                    .scaleY(0f);
        }
    }

    private void animateButtonsIn() {
        for (int i = 0; i < relativetext.getChildCount(); i++) {
            View child = relativetext.getChildAt(i);
            child.animate()
                    .setStartDelay(100 + i * DELAY)
                    .setInterpolator(interpolator)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }
    }

    private void scaleText(){
        boolean flag = false;
        float startSize = 0;
        float endSize = 0;
        if (text_process.getText().toString().length() == 1){
            flag = true;
            startSize = 25;
            endSize = 42;
        }
        if (text_process.getText().toString().length() == 8){
            flag = true;
            startSize = 42;
            endSize = 25;
        }
        if (flag == true)
        {
            final int animationDuration = 600; // Animation duration in ms

            ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
            animator.setDuration(animationDuration);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    text_process.setTextSize(animatedValue);
                }
            });

            animator.start();
        }
    }

    private void scaleDownText(){
        if (text_process.getText().toString().length() == 7) {
            final float startSize = 25;
            final float endSize = 42;
            final int animationDuration = 600; // Animation duration in ms

            ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
            animator.setDuration(animationDuration);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    text_process.setTextSize(animatedValue);
                }
            });

            animator.start();
        }
    }
}
