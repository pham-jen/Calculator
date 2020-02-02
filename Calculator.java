/**
 * @author jenpham
 * Calculator class simulates a real working calculator GUI */
package Project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener 
{
	// Creates each button to be displayed
	private JButton numberButtons[] = new JButton[10];
	private String captions[] = {"1","2","3",
									"4","5","6",
									"7","8","9",
									"0"};
	private JButton addButton = new JButton("+");
	private JButton subButton = new JButton("-");
	private JButton divButton = new JButton("/");
	private JButton multButton = new JButton("*");
	private JButton clearButton = new JButton("AC");
	private JButton equalsButton = new JButton("=");
	private JButton squareRootButton = new JButton("sqrt");
	private JButton backButton = new JButton("back");
	
	// Organization of how the buttons will be displayed on the frame
	final int ROWS = 6;
	final int COLUMNS = 3;
	private JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
	private JTextField output = new JTextField(20);
	Container frame;
	
	// variables that will be changed during calculations
	private double firstNumber;
	private double secondNumber;
	
	/**
	 * Adds every button, text field, and its event handler onto the panel as well as default settings 
	 * for the frame*/
	public Calculator()
	{
		// Adds each button onto the panel
		frame = getContentPane();
		for (int count = 0; count < 10; count++)
		{
			numberButtons[count] = new JButton(captions[count]);
			buttonPanel.add(numberButtons[count]);
			numberButtons[count].addActionListener(this);
		}
		
		buttonPanel.add(addButton);
		addButton.addActionListener(this);
		
		buttonPanel.add(subButton);
		subButton.addActionListener(this);
		
		buttonPanel.add(divButton);
		divButton.addActionListener(this);
		
		buttonPanel.add(multButton);
		multButton.addActionListener(this);
		
		buttonPanel.add(clearButton);
		clearButton.addActionListener(this);
		
		buttonPanel.add(equalsButton);
		equalsButton.addActionListener(this);
		
		buttonPanel.add(squareRootButton);
		squareRootButton.addActionListener(this);
		
		buttonPanel.add(backButton);
		backButton.addActionListener(this);
		
		// Text field can only be changed by pressing buttons
		output.setEditable(false);
		
		// The output will be displayed by a text field that is always at the top of the frame
		frame.setLayout(new BorderLayout());
		frame.add(output,BorderLayout.NORTH);
		
		// The buttons will always be centered in the frame
		frame.add(buttonPanel, BorderLayout.CENTER);
		
		// By clicking the upper left button, the program will exit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// The frame will adjust automatically when manually resized
		pack();
		
		// Allows the frame to be visible
		setVisible(true);
	}

	@Override
	/** Performs the correct calculation based on the button that was pressed
	 * @param e the event that was generated */
	public void actionPerformed(ActionEvent e) 
	{
		String previousOutput = output.getText();
		String currentOutput = "";
		
		// Gets the source of which button was clicked
		JButton buttonSource = (JButton) e.getSource();
		
		switch (buttonSource.getActionCommand())
		{
			case "0":
				currentOutput = "0";
				break;
			case "1":
				currentOutput = "1";
				break;
			case "2":
				currentOutput = "2";
				break;
			case "3":
				currentOutput = "3";
				break;
			case "4":
				currentOutput = "4";
				break;
			case "5":
				currentOutput = "5";
				break;
			case "6":
				currentOutput = "6";
				break;
			case "7":
				currentOutput = "7";
				break;
			case "8":
				currentOutput = "8";
				break;
			case "9":
				currentOutput = "9";
				break;
			case "AC":
				previousOutput = "";
				break;
			case "+":
				currentOutput = "+";
				firstNumber = Integer.parseInt(output.getText());
				break;
			case "-":
				currentOutput = "-";
				firstNumber = Integer.parseInt(output.getText());
				break;
			case "/":
				currentOutput = "/";
				firstNumber = Integer.parseInt(output.getText());
				break;
			case "*":
				currentOutput = "*";
				firstNumber = Integer.parseInt(output.getText());
				break;
			case "sqrt":
				currentOutput = "sqrt";
				previousOutput = "";
				break;
			case "back":
				previousOutput = previousOutput.substring(0,previousOutput.length()-1);
				break;
			case "=":
				/* Gets the current content in the text field and separates the two numbers
				 * and finds the operator used, and then perform the correct calculation upon
				 * those numbers. */
				String currentTextField = output.getText();
				String newText = "";
				previousOutput = "";
				
				for (int index = currentTextField.length() - 1; index >= 0; index--)
				{
					if (Character.isDigit(currentTextField.charAt(index)))
					{
						newText += currentTextField.charAt(index);
					}
					else if (currentTextField.charAt(index) == '+')
					{
						currentOutput = newText;
						secondNumber = Integer.parseInt(currentOutput);
						currentOutput = sum((int)firstNumber, (int)secondNumber);
						break;
					}
					else if (currentTextField.charAt(index) == '-')
					{
						currentOutput = newText;
						secondNumber = Integer.parseInt(currentOutput);
						currentOutput = difference((int)firstNumber, (int)secondNumber);
						break;
					}
					else if (currentTextField.charAt(index) == '*')
					{
						currentOutput = reverse(newText);
						secondNumber = Integer.parseInt(currentOutput);
						currentOutput = product((int)firstNumber, (int)secondNumber);
						break;
					}
					else if (currentTextField.charAt(index) == '/')
					{
						currentOutput = reverse(newText);
						secondNumber = Double.parseDouble(currentOutput);
						currentOutput = quotient(firstNumber, secondNumber);
						break;
					}
					else if (currentTextField.charAt(index) == 't')
					{
						currentOutput = reverse(newText);
						currentOutput = Double.toString(Math.sqrt(Double.parseDouble((currentOutput))));
						break;
					}
					else if (currentTextField.charAt(index) == 'k')
					{
						currentOutput = newText.substring(0,newText.length()-1);
						break;
					}
				}
		}
		output.setText(previousOutput + currentOutput);
	}
	
	/** Calculates the sum of two numbers
	 * @param first the first number in the equation 
	 * @param second the second number in the equation
	 * @return sum the String representation of the answer */
	private String sum(int first, int second)
	{
		String sum = Integer.toString(first + second);
		return sum;
	}
	
	/** Calculates the difference of two numbers
	 * @param first the first number in the equation 
	 * @param second the second number in the equation
	 * @return difference the String representation of the answer */
	private String difference(int first, int second)
	{
		String difference = Integer.toString(first - second);
		return difference;
	}
	
	/** Calculates the product of two numbers
	 * @param first the first number in the equation 
	 * @param second the second number in the equation
	 * @return product the String representation of the answer */
	private String product(int first, int second)
	{
		String product = Integer.toString(first * second);
		return product;
	}
	
	/** Calculates the quotient of two numbers
	 * @param dividend the number to be divided
	 * @param divisor the number that divides
	 * @return quotient the String representation of the answer */
	private String quotient(double dividend, double divisor)
	{
		String quotient = Double.toString(dividend / divisor);
		return quotient;
	}
	
	/** Reverses a string
	 * @param string the string that is to be reversed
	 * @return newString the reversed string */
	private String reverse(String string)
	{
		String newString = "";
		for (int index = string.length() - 1; index >= 0; index--)
		{
			newString += string.charAt(index);
		}
		return newString;
	}
	
	public static void main(String[] args) 
	{
		Calculator calculator = new Calculator();
	}

}
