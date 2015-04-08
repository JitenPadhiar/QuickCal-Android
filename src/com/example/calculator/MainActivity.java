

package com.example.calculator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
   
	boolean clear = true;								
	boolean operator = false ;
	boolean insert = false;
	boolean last_click = false;
	float input1 = 0f;
	float input2 = 0f;
	float result = 0f;
	String Operator = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insert_text(String text){
    	EditText screen = (EditText) findViewById(R.id.display);
    	if (this.clear){
    		screen.setText("");
    		this.clear = false;
    	}
    	this.insert = true;
    	this.last_click = true;
    	screen.append(text);
    }
    public void set_operator(String operator){
    	EditText screen = (EditText) findViewById(R.id.display);
    	if (screen.getText().toString().equals(".")) screen.setText("0");
    	if (this.insert && this.operator && this.last_click){
    		calculator();
    	}
    	if (screen.getText().toString().length() > 0 ){
    		this.input1 = Float.parseFloat(screen.getText().toString());
    	}
    	this.operator = true;
		this.clear = true;
		this.last_click = false;
		if (operator.equals("+"))		this.Operator = "+";
		else if (operator.equals("-"))	this.Operator = "-";
		else if (operator.equals("*"))	this.Operator = "*";
		else if (operator.equals("/"))	this.Operator = "/";
		
    }
  
    public void calculator(){
    	EditText screen = (EditText) findViewById(R.id.display);
    	if (screen.getText().toString().equals(".")){
    		screen.setText("0");
    	}
    	if (screen.getText().toString().length() > 0){
    		this.input2 = Float.parseFloat(screen.getText().toString());
    	}
    	if (this.Operator.equals("+")) {
			this.result = this.input1 + this.input2;
		} else if (this.Operator.equals("-")){
			this.result = this.input1 - this.input2;	
		} else if (this.Operator.equals("*")){
			this.result =this.input1 * this.input2;
		} else if (this.Operator.equals("/")){
			this.result = this.input1 / this.input2;
		}else{
			this.result = Float.parseFloat(screen.getText().toString());
		}

		screen.setText(this.result + "");
    }
    public void ButtonClickHandler(View v){
    	EditText screen = (EditText) findViewById(R.id.display);
    	switch(v.getId()){
    		case R.id.zero : insert_text("0"); break;
    		case R.id.one : insert_text("1"); break;
    		case R.id.two : insert_text("2"); break;
    		case R.id.three : insert_text("3"); break;
    		case R.id.four : insert_text("4"); break;
    		case R.id.five : insert_text("5"); break;
    		case R.id.six : insert_text("6"); break;
    		case R.id.seven : insert_text("7"); break;
    		case R.id.eight : insert_text("8"); break;
    		case R.id.nine : insert_text("9"); break;
    		case R.id.dot : 
    			if (!screen.getText().toString().contains(".") || this.operator){
    				insert_text("."); 
    			}
    			break;
    		case R.id.add : 	set_operator("+"); break;
    		case R.id.sub :	set_operator("-"); break;
    		case R.id.mul:	set_operator("*"); break;
    		case R.id.div:	set_operator("/"); break;
    		
    		case R.id.equal:	
    			if(screen.getText().toString().length() > 0 && this.Operator != ""){
    				calculator(); 
    				this.clear = true;
    				this.input1 = 0f; 
    				this.input2 = 0f;
    				this.Operator = "";
    				this.operator = false ;
    			}
    			break;
    		case R.id.del:
    			if(screen.getText().toString().length() > 1){
	    			 String screen_new = screen.getText().toString().substring(0, screen.getText().toString().length()-1);
	    			 screen.setText(screen_new);
	    			 this.clear = false;
   			 	}else{
   			 		 screen.setText("0");
   			 		this.clear = true;
   			 	}
	   			 break;
    		case R.id.clear:
    			this.input1 = 0f;
    			this.input2 = 0f;
    			this.result = 0f;
    			this.Operator = "";
    			this.operator = false ;
    			this.insert = false;
    			this.last_click = false;
    			this.clear = true;
    			screen.setText("0");
    			break;
    	}
    }
}