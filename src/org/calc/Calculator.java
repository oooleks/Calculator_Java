package org.calc;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculator {
	// Объявление всех компонентов калькулятора.
	JPanel windowContent;
	JFormattedTextField displayField, displayField2;
	JButton buttonPoint;
	JButton buttonEqual;
	JButton buttonPlus;
	JButton buttonMinus;
	JButton buttonDivide;
	JButton buttonMultiply;
	JButton buttonSqrt;
	JButton buttonClear;
	JPanel p1, p2, p3;
	
	
// В конструкторе создаются все компоненты
// и добавляются на фрейм с помощью комбинации
// Borderlayout и Gridlayout

Calculator(){
windowContent = new JPanel();

// Задаём схему для этой панели
BorderLayout bl = new BorderLayout();
windowContent.setLayout(bl);

// Создаём и отображаем поле
// Добавляем его в Северную область окна
displayField = new JFormattedTextField();
displayField.setHorizontalAlignment(JFormattedTextField.RIGHT);
windowContent.add("North",displayField);
displayField2 = new JFormattedTextField();
displayField2.setHorizontalAlignment(JFormattedTextField.RIGHT);

// Создаём кнопки, используя конструктор
// класса JButton, который принимает текст
// кнопки в качестве параметра
JButton[] numButtons= new JButton[10];
String b;
for (int i=0; i<10; i++) {
	b = Integer.toString(i);
	numButtons[i] = new JButton(b);
}

buttonPoint   = new JButton(".");
buttonEqual   = new JButton("=");
buttonPlus    = new JButton("+");
buttonMinus   = new JButton("-");
buttonDivide  = new JButton("/");
buttonMultiply= new JButton("*");
buttonSqrt    = new JButton("SQRT");
buttonClear   = new JButton("C");

// Создаём панель с GridLayout
// которая содержит 12 кнопок - 10 кнопок с числами
// и кнопки с точкой и знаком равно
p1 = new JPanel();
GridLayout gl1 = new GridLayout(4,3);
p1.setLayout(gl1);

p2 = new JPanel();
GridLayout gl2 = new GridLayout(4,1);
p2.setLayout(gl2);

p3 = new JPanel();
GridLayout gl3 = new GridLayout(1,2);
p3.setLayout(gl3);

// Добавляем кнопки на панель p1
//for (int i=9; i>0; i--) p1.add(numButtons[i]);

p1.add(numButtons[7]);
p1.add(numButtons[8]);
p1.add(numButtons[9]);
p1.add(numButtons[4]);
p1.add(numButtons[5]);
p1.add(numButtons[6]);
p1.add(numButtons[1]);
p1.add(numButtons[2]);
p1.add(numButtons[3]);
p1.add(numButtons[0]);
p1.add(buttonPoint);
p1.add(buttonEqual);
p2.add(buttonDivide);
p2.add(buttonMultiply);
p2.add(buttonMinus);
p2.add(buttonPlus);
p3.add(buttonSqrt);
p3.add(buttonClear);
p3.add(displayField2);

// Создаем экземпляр слушателя событий и
// регистрируем его в каждой кнопке
CalculatorEngine calcEngine = new CalculatorEngine(this);
for (int i=0; i<10; i++) numButtons[i].addActionListener(calcEngine);
buttonPoint.addActionListener(calcEngine);
buttonEqual.addActionListener(calcEngine);
buttonPlus.addActionListener(calcEngine);
buttonMinus.addActionListener(calcEngine);
buttonDivide.addActionListener(calcEngine);
buttonMultiply.addActionListener(calcEngine);
buttonSqrt.addActionListener(calcEngine);
buttonClear.addActionListener(calcEngine);

// Помещаем панель p1, p2, p3 в области окна
windowContent.add("Center",p1);
windowContent.add("East",p2);
windowContent.add("South",p3);
displayField.setText("0");

// Создаём фрейм и задаём его основную панель
JFrame frame = new JFrame("Calculator");
frame.setContentPane(windowContent);

// делаем размер окна достаточным
// для того, чтобы вместить все компоненты
frame.pack();
frame.setSize(230, (int) frame.getSize().getHeight());
// Наконец, отображаем окно
frame.setVisible(true);
}
	public static void main(String[] args) {
		Calculator calc = new Calculator();

	}
}