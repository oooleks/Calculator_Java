package org.calc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class CalculatorEngine implements ActionListener {
	
	Calculator parent;
	
	// Конструктор сохраняет ссылку на окно калькулятора
	// в переменной экземпляра класса
	CalculatorEngine(Calculator parent){
		this.parent = parent;}
	
	char selectedAction = ' ';
	double memoryResult = 0;
	double currentResult = 0;
	boolean byZero, onEqual = false;

	public void actionPerformed(ActionEvent e){
		
		// Get source of event
		JButton clickedButton = (JButton) e.getSource();
		
		String dispFieldText = parent.displayField.getText();
		double displayValue = 0;
		
		if (!"".equals(dispFieldText)) {
			if (!byZero) displayValue = Double.parseDouble(dispFieldText);
			else {
				displayValue = 0;
				byZero = false;
				selectedAction = ' ';
				currentResult = 0;
				memoryResult = 0;
				parent.displayField2.setText("");
			}
		}

		Object src = e.getSource();
		
		if (src == parent.buttonPlus){
			selectedAction = '+';
			currentResult = displayValue;
			parent.displayField.setText("");
			parent.displayField2.setText("+");
			onEqual = false;

		} else if (src == parent.buttonMinus){
			selectedAction = '-';
			currentResult = displayValue;
			parent.displayField.setText("");
			parent.displayField2.setText("-");
			onEqual = false;
		
		} else if (src == parent.buttonDivide){
			selectedAction = '/';
			currentResult = displayValue;
			parent.displayField.setText("");
			parent.displayField2.setText("/");
			onEqual = false;
		
		} else if (src == parent.buttonMultiply){
			selectedAction = '*';
			currentResult = displayValue;
			parent.displayField.setText("");
			parent.displayField2.setText("*");
			onEqual = false;
		
		} else if (src == parent.buttonSqrt){
			selectedAction = 'Q';
			currentResult = Math.sqrt(displayValue);
			memoryResult = currentResult;
			parent.displayField.setText("" + currentResult);
			parent.displayField2.setText("sqrt()");
			onEqual = true;
				
		} else if (src == parent.buttonPoint){
			if (onEqual) parent.displayField2.setText("");
			
			//Check for no more than one "." entered
			String s = String.valueOf(dispFieldText+clickedButton.getText());
			int counter = 0;
			for (int i=0; i<s.length(); i++ ) {
				if( s.charAt(i) == '.' ) {
					counter++;
					}
				}

			if (displayValue == 0 | onEqual) {
				parent.displayField.setText("0.");
				onEqual = false;
				}
			else if (counter == 1){ 
				parent.displayField.setText(dispFieldText + clickedButton.getText());
				}
					
		} else if (src == parent.buttonClear){
			parent.displayField.setText("0");
			parent.displayField2.setText("");
			currentResult = 0;
			memoryResult = 0;
			selectedAction = ' ';
			byZero = false;
			onEqual = false;
						
		} else if (src == parent.buttonEqual){
			onEqual = true;
			parent.displayField2.setText("");
			
			if (selectedAction == '+'){
				currentResult += memoryResult;
				parent.displayField.setText("" + currentResult);
			
			} else if (selectedAction == '-'){
				currentResult -= memoryResult;
				parent.displayField.setText("" + currentResult);		
			
			} else if (selectedAction == ' '){
				parent.displayField.setText("" + memoryResult);
												
			} else if (selectedAction == '*'){
				currentResult *= memoryResult;
				parent.displayField.setText("" + currentResult);
								
			// Checking if divide by 0
			} else if (selectedAction == '/'){
				if (memoryResult != 0) {
					currentResult /= memoryResult;
					parent.displayField.setText("" + currentResult);
				} else {
					parent.displayField.setText("Cannot divide by zero");
					byZero = true;
					}
			}
			
		} else {
			if (onEqual) {
				dispFieldText = "";
				onEqual = false;
			}

			String clickedButtonLabel = clickedButton.getText();
			
			// Check for "00.." condition and transform it to "0"
			if ((dispFieldText+clickedButtonLabel).length() > 1 & displayValue == 0 & 
					String.valueOf(dispFieldText).indexOf('.') != 1) {
				parent.displayField.setText(clickedButtonLabel);
				memoryResult = Double.parseDouble(clickedButtonLabel);
								
			} else {
				parent.displayField.setText(dispFieldText + clickedButtonLabel);
				memoryResult = Double.parseDouble(dispFieldText + clickedButtonLabel);}
			}
		
	} // end of actionPerformed method
	
} // end of CalculatorEngine class
