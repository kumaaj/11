/*
Adam Khan
COP2251C
Due Date: 4/7/2024
*/
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class project extends JFrame
  {
    private JLabel lblAccNumber, lblName, lblAddress, lblPhoneNumber, lblTradeIn, lblTotal;
    
    private JTextField txtName, txtAddress, txtPhoneNumber, txtTradeIn;
    
    private JButton calculateBtn, exitBtn; //Exit button to select.
    
    private JCheckBox s40CB, s60CB, s70CB, s80CB, financingCB, metallicCB, packageBCB; //Check Box list  of options!

    private JRadioButton yes, no; //Radio Button for Cash payment
    
    private double metallicPaintAmount = 650.00; //metalic paint $650
    private double titleTags = 325.00; //Title & Tags $325
    private double payingByCashAmount = 750.00; //750 discount if customer chooses to pay by cash
    private double packageB = 3250;
    private double saleTax = 0.06; //6% sales tax
    private double financing = 0.07; //7% financing

    //Constructor:
    public project()
    {
      setTitle("Car Dealership");
      setSize(400, 425);
      setLayout(new FlowLayout());
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //setLayout(new GridLayout(7, 2));
      setVisible(true);

      //RNG for Account Number, 0 to 7 digits long (0 ~ 1,000,000):
      Random random = new Random();
      int accNumber = random.nextInt(999999) + 1;
      lblAccNumber = new JLabel("Account Number: A" + accNumber + "\n");
      add(lblAccNumber);
      
      //Labels:
      lblName = new JLabel("Name: ");
      lblAddress = new JLabel("Address: ");
      lblPhoneNumber = new JLabel("Phone Number: ");
      lblTradeIn = new JLabel("Trade in: ");

      //Text Fields:
      txtName = new JTextField(10);
      txtAddress = new JTextField(10);
      txtPhoneNumber = new JTextField(10);
      txtTradeIn = new JTextField(10);

      //Check Boxes:
      s40CB = new JCheckBox("s40, $27,700");
      s60CB = new JCheckBox("s60, $32,500");
      s70CB = new JCheckBox("s70, $36,000");
      s80CB = new JCheckBox("s80, $44,000");
      financingCB = new JCheckBox("Financing, @ 7%");
      metallicCB = new JCheckBox("Metallic Paint Job, $650");
      packageBCB = new JCheckBox("Optional: Package B! (Only for s70 & s80), $3,250");

      //Radio Buttons:
      yes = new JRadioButton("Yes, paying by cash.");
      no = new JRadioButton("No, not paying by cash.");

      //Grouping Radio Buttons:
      ButtonGroup cashGroup = new ButtonGroup();
      cashGroup.add(yes);
      cashGroup.add(no);
      
      //Calculate Button:
      calculateBtn = new JButton("Calculate!");
      //Exit Button:
      exitBtn = new JButton("Exit!");

      //Adds Labels & Text Fields to frame:
      add(lblName);
      add(txtName);
      add(lblAddress);
      add(txtAddress);
      add(lblPhoneNumber);
      add(txtPhoneNumber);
      add(lblTradeIn);
      add(txtTradeIn);

      //Adds Check Boxes to Frame:
      add(s40CB);
      add(s60CB);
      add(s70CB);
      add(s80CB);
      add(financingCB);
      add(metallicCB);
      add(packageBCB);

      //Adds Radio Buttons to Frame:
      add(yes);
      add(no);

      //Add Calculate Button to Frame:
      add(calculateBtn);

      //Adds Exit Button to Frame:
      add(exitBtn);   

      lblTotal = new JLabel("Total: ");
      add(lblTotal);


      //Add Action Listener for Calculate Button:
      calculateBtn.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            calculateTotal();
          }
        });      
      
      //Add Action Listener for Exit Button:
      exitBtn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          dispose(); //Closes window
        }
      });
    }

    //Calculate for the Calculate button:
    private void calculateTotal()
    {
      double carAmount = 0.0;
      //Multiple cars could be purchased, if so, they will added accumulatively to the total.
      if (s40CB.isSelected())
      {
        carAmount += 27700;
      }
      else if (s60CB.isSelected())
      {
        carAmount += 32500;
      }
      else if (s70CB.isSelected())
      {
        carAmount += 36000;
      }
      else if (s80CB.isSelected())
      {
        carAmount += 44000;
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Please select a car from above!");
        return;
      }

      //If-loop for Package B if it was selected with s70 & s80:
      if (packageBCB.isSelected())
      {
        if (s70CB.isSelected() || s80CB.isSelected())
        {
          carAmount += packageB;
        }
        else
        {
          JOptionPane.showMessageDialog(this, "s70 or s80 must be selected for Package B!");
          return;
        }
      }

      //Trade in amount (if any exists):
      double tradeInAmount = 0.0;
      try
        {
          tradeInAmount = Double.parseDouble(txtTradeIn.getText());
        } catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog(this, "Please enter a valid trade-in amount!");
          return;
        }
      //Deduce difference from trade-in amount
      carAmount -= tradeInAmount;

      if (metallicCB.isSelected()) //If metallic paint was selected
      {
        carAmount += metallicPaintAmount;
      }

      if (financingCB.isSelected()) //If financing was selected
      {
        carAmount += (carAmount * financing);
      }

      //Radio Button if-loop
      if (yes.isSelected())
      {
        carAmount -= payingByCashAmount;
      }
      else if (no.isSelected())
      {
        carAmount = carAmount;
      }

      //MATH:
      double taxAmount = carAmount * saleTax;
      double finalCost = carAmount + taxAmount + titleTags;

      // lblTotal.setText("Final Cost: " + finalCost);
      lblTotal.setText("Final Cost: " + String.format("%.2f", finalCost));
    }

    //Main method:
    public static void main(String[] args)
    {
      new project();
    }
  }