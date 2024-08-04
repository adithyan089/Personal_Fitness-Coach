package Project_last;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;

public class PersonalFitnessCoach extends JFrame {
    // UI Elements
    private static Image backgroundImage;
    private JTextField nameField, ageField, weightField, heightField, waistField;
    private static JButton calculateBmiButton;
    private static JButton calculateBodyFatButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resetButton;
    private JComboBox<String> goalComboBox, genderComboBox, activityLevelComboBox;
    private JLabel bmiResultLabel, calorieGoalLabel, bfpLabel;
    private static final double neckCircumference = 39;
    private static final double hipCircumference =74;
    // Formatting
    private DecimalFormat bmiFormat = new DecimalFormat("#.##");
    public PersonalFitnessCoach(Image logoImage, JButton calculateCalorieGoalButton, JButton generateDietAndWorkoutPlan) {
        // UI Setup
        setLayout(new BorderLayout());
        // Load images
        backgroundImage = new ImageIcon("D:\\SEM2\\22AIE111- OOP IN Java\\PROJECT\\Project_last\\FIT.png").getImage();
        logoImage = new ImageIcon("D:\\SEM2\\22AIE111- OOP IN Java\\PROJECT\\Project_last\\fitnesslogo.png.png").getImage();

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //logo to the panel
        ImageIcon logoIcon = new ImageIcon(logoImage);
        Image scaledLogoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogoImage));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25, 0, 20, 0);    
        backgroundPanel.add(logoLabel, c);

        // container panel for user information
        JPanel userInfoContainer = new JPanel();
        userInfoContainer.setBackground(new Color(35, 35, 35, 150));
        userInfoContainer.setLayout(new BorderLayout());
        userInfoContainer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.lightGray), "User Info",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 18), Color.white));
        userInfoContainer.setOpaque(false);

        // Add user information components
        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBackground(new Color(240, 240, 240, 180));
        c = new GridBagConstraints();
        c.insets = new Insets(15, 15, 15, 15);
        c.anchor = GridBagConstraints.CENTER;
//Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 0;
        userInfoPanel.add(nameLabel, c);
        nameField = new JTextField(10);
        nameField.setBackground(Color.lightGray);
        nameField.setForeground(Color.BLACK);
        nameField.setFont(new Font("Arial", Font.BOLD, 16));
        c.gridx = 1;
        c.gridy = 0;
        userInfoPanel.add(nameField, c);
//Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ageLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 1;
        userInfoPanel.add(ageLabel, c);
        ageField = new JTextField(10);
        ageField.setFont(new Font("Arial", Font.BOLD, 16));
        ageField.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 1;
        userInfoPanel.add(ageField, c);
//weight
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 16));
        weightLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 2;
        userInfoPanel.add(weightLabel, c);
        weightField = new JTextField(10);
        weightField.setFont(new Font("Arial", Font.BOLD, 16));
        weightField.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 2;
        userInfoPanel.add(weightField, c);

      //Height
        JLabel heightLabel = new JLabel("Height (m):");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 16));
        heightLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 3;
        userInfoPanel.add(heightLabel, c);
        heightField = new JTextField(10);
        heightField.setFont(new Font("Arial", Font.BOLD, 16));
        heightField.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 3;
        userInfoPanel.add(heightField, c);

        // Gender Dropdown
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 16));
        genderLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 4;
        userInfoPanel.add(genderLabel, c);
        String[] genders = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBackground(Color.lightGray);
        genderComboBox.setForeground(Color.BLACK);
        genderComboBox.setFont(new Font("Arial", Font.BOLD, 16));
        c.gridx = 1;
        c.gridy = 4;
        userInfoPanel.add(genderComboBox, c);

        // Fitness Goals
        JLabel goalLabel1 = new JLabel("Fitness Goal:");
        goalLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        goalLabel1.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 5;
        userInfoPanel.add(goalLabel1, c);
        String[] goals = {"Lose Weight", "Gain Muscle", "Maintain Fitness"};
        goalComboBox = new JComboBox<>(goals);
        goalComboBox.setBackground(Color.lightGray);
        goalComboBox.setForeground(Color.BLACK);
        goalComboBox.setFont(new Font("Arial", Font.BOLD, 16));
        c.gridx = 1;
        c.gridy = 5;
        userInfoPanel.add(goalComboBox, c);

        // Activity Level Dropdown
        JLabel activityLabel = new JLabel("Activity Level:");
        activityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        activityLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 6;
        userInfoPanel.add(activityLabel, c);
        String[] activityLevels = {"Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Extra Active"};
        activityLevelComboBox = new JComboBox<>(activityLevels);
        activityLevelComboBox.setBackground(Color.lightGray);
        activityLevelComboBox.setForeground(Color.BLACK);
        activityLevelComboBox.setFont(new Font("Arial", Font.BOLD, 16));
        c.gridx = 1;
        c.gridy = 6;
        userInfoPanel.add(activityLevelComboBox, c);

        // Waist Circumference Label
        JLabel waistLabel = new JLabel("Waist Circumference (cm):");
        waistLabel.setFont(new Font("Arial", Font.BOLD, 16));
        waistLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 7;
        userInfoPanel.add(waistLabel, c);
        waistField = new JTextField(10);
        waistField.setFont(new Font("Arial", Font.BOLD, 16));
        waistField.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 7;
        userInfoPanel.add(waistField, c);

        // Save Button
        saveButton = new JButton("Save Data");
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 8;
        userInfoPanel.add(saveButton, c);

        // Load Button
        loadButton = new JButton("Load Data");
        loadButton.setBackground(Color.MAGENTA);
        loadButton.setForeground(Color.BLACK);
        loadButton.setFont(new Font("Arial", Font.BOLD, 16));
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 8;
        c.anchor = GridBagConstraints.SOUTHEAST;
        userInfoPanel.add(loadButton, c);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setBackground(Color.RED);
        resetButton.setForeground(Color.BLACK);
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        userInfoPanel.add(resetButton, c);
        
         // Generates Plan Button
         generateDietAndWorkoutPlan = new JButton("Generate Plan");
         generateDietAndWorkoutPlan.setBackground(Color.YELLOW);
         generateDietAndWorkoutPlan.setForeground(Color.BLACK);
         generateDietAndWorkoutPlan.setFont(new Font("Arial", Font.BOLD, 16));
         generateDietAndWorkoutPlan.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 generateDietAndWorkoutPlan();
             }
         });
         c.gridx = 1;
         c.gridy = 9;
         userInfoPanel.add(generateDietAndWorkoutPlan, c);
 
         // Adds user information panel to the container
         userInfoContainer.add(userInfoPanel, BorderLayout.CENTER);
        //Adds user info panel to the container panel
        userInfoContainer.add(userInfoPanel, BorderLayout.NORTH);

        // Panel for BMI, Body Fat and Calorie Goal calculation
        JPanel calculationPanel = new JPanel(new GridBagLayout());
        calculationPanel.setBackground(new Color(0, 0, 0, 150));
        calculationPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.lightGray), "Calculations",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 18), Color.white));
        calculationPanel.setOpaque(false);

        // Panel for BMI Calculation
        JPanel bmiPanel = new JPanel(new GridBagLayout());
        bmiPanel.setBackground(new Color(0, 0, 0, 150));
        bmiPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.lightGray), "BMI Calculation",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 16), Color.white));
        bmiPanel.setOpaque(false);
        c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;

        calculateBmiButton = new JButton("Calculate BMI");
        calculateBmiButton.setBackground(Color.GREEN);
        calculateBmiButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateBmiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        bmiPanel.add(calculateBmiButton, c);

        bmiResultLabel = new JLabel("BMI -");
        bmiResultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bmiResultLabel.setForeground(Color.lightGray);
        c.gridy = 1;
        bmiPanel.add(bmiResultLabel, c);

        // Panel for Body Fat Percentage Calculation
        JPanel bodyFatPanel = new JPanel(new GridBagLayout());
        bodyFatPanel.setBackground(new Color(0, 0, 0, 150));
        bodyFatPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.lightGray), "Body Fat Calculation",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 16), Color.white));
        bodyFatPanel.setOpaque(false);
        c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;

        calculateBodyFatButton = new JButton("Calculate Body Fat");
        calculateBodyFatButton.setBackground(Color.GREEN);
        calculateBodyFatButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateBodyFatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateBodyFat();
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        bodyFatPanel.add(calculateBodyFatButton, c);

        bfpLabel = new JLabel("Body Fat % - ");
        bfpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bfpLabel.setForeground(Color.lightGray);
        c.gridy = 1;
        bodyFatPanel.add(bfpLabel, c);

        // Panel for Calorie Goal Calculation
        JPanel caloriePanel = new JPanel(new GridBagLayout());
        caloriePanel.setBackground(new Color(0, 0, 0, 150));
        caloriePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.lightGray), "Calorie Goal Calculation",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 16), Color.white));
        caloriePanel.setOpaque(false);
        c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;

        calculateCalorieGoalButton = new JButton("Calculate Calorie Goal");
        calculateCalorieGoalButton.setBackground(Color.GREEN);
        calculateCalorieGoalButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateCalorieGoalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCalorieGoal();
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        caloriePanel.add(calculateCalorieGoalButton, c);

        calorieGoalLabel = new JLabel("Daily calorie goal -");
        calorieGoalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        calorieGoalLabel.setForeground(Color.lightGray);
        c.gridy = 1;
        caloriePanel.add(calorieGoalLabel, c);

        // Panels to the calculation panel in a single row
        JPanel calculationContainer = new JPanel();
        calculationContainer.setLayout(new GridLayout(1, 3));
        calculationContainer.setOpaque(false);
        calculationContainer.add(bmiPanel);
        calculationContainer.add(bodyFatPanel);
        calculationContainer.add(caloriePanel);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(15, 15, 15, 15);
        c.anchor = GridBagConstraints.CENTER;
        calculationPanel.add(calculationContainer, c);
        userInfoContainer.add(calculationPanel, BorderLayout.CENTER);

        // user info container to the background panel
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(userInfoContainer, c);
    
        //  background panel to the frame
        add(backgroundPanel);
    
        // Frame settings
        setTitle("Personal Fitness Coach");
        setSize(1200,1600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // Custom panel to display background image
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    // Calculation methods
    private double calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double bmi = weight / (height * height);
            bmiResultLabel.setText("BMI: " + bmiFormat.format(bmi));
            
            // Determine BMI category
            String bmiCategory;
            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                bmiCategory = "Normal weight";
            } else if (bmi >= 25 && bmi <= 29.9) {
                bmiCategory = "Overweight";
            } else {
                bmiCategory = "Obese";
            }
            
            // Display alert box with BMI category
            JOptionPane.showMessageDialog(this, "Your BMI is " + bmiFormat.format(bmi) + " (" + bmiCategory+")", "BMI Category", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException e) {
            bmiResultLabel.setText("Invalid input");
        }
        return 0;
    }
    private void calculateBodyFat() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double waist = Double.parseDouble(waistField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            double bodyFatPercentage = 0;

            if ("Male".equals(gender)) {
                bodyFatPercentage = (495 / (1.0324 - 0.19077 * Math.log10(waist - neckCircumference) + 0.15456 * Math.log10(HEIGHT)) - 450)/10;
            } else if ("Female".equals(gender)) {
                bodyFatPercentage = (495 / (1.29579 - 0.35004 * Math.log10(waist + hipCircumference - neckCircumference) + 0.22100 * Math.log10(HEIGHT)) - 450)/10;
            }
            bfpLabel.setText("Body Fat %: " + bmiFormat.format(bodyFatPercentage) + "%");
        } catch (NumberFormatException e) {
            bfpLabel.setText("Invalid input");
        }
    }

    private void calculateCalorieGoal() {
        try {
            String goal = (String) goalComboBox.getSelectedItem();
            String gender = (String) genderComboBox.getSelectedItem();
            String activityLevel = (String) activityLevelComboBox.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double bmr = 0;

            if ("Male".equals(gender)) {
                bmr = 88.362 + (13.397 * weight) + (4.799 * height * 100) - (5.677 * age);
            } else if ("Female".equals(gender)) {
                bmr = 447.593 + (9.247 * weight) + (3.098 * height * 100) - (4.330 * age);
            }

            double calorieGoal = bmr;

            switch (activityLevel) {
                case "Sedentary":
                    calorieGoal *= 1.2;
                    break;
                case "Lightly active":
                    calorieGoal *= 1.375;
                    break;
                case "Moderately active":
                    calorieGoal *= 1.55;
                    break;
                case "Very active":
                    calorieGoal *= 1.725;
                    break;
                case "Extra active":
                    calorieGoal *= 1.9;
                    break;
            }

            switch (goal) {
                case "Lose weight":
                    calorieGoal -= 500;
                    break;
                case "Gain weight":
                    calorieGoal += 500;
                    break;
            }

            calorieGoalLabel.setText("Daily calorie goal: " + bmiFormat.format(calorieGoal));
        } catch (NumberFormatException e) {
            calorieGoalLabel.setText("Invalid input");
        }
    }

   // File handling methods
private void saveFile() {
    // Save userS data to a file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save User Data");
    
    int userSelection = fileChooser.showSaveDialog(this);
    
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
            writer.println("Name: " + nameField.getText());
            writer.println("Age: " + ageField.getText());
            writer.println("Weight: " + weightField.getText());
            writer.println("Height: " + heightField.getText());
            writer.println("Gender: " + genderComboBox.getSelectedItem());
            writer.println("Activity Level: " + activityLevelComboBox.getSelectedItem());
            writer.println("Fitness Goal: " + goalComboBox.getSelectedItem());
            writer.println("Waist Circumference: " + waistField.getText());
            JOptionPane.showMessageDialog(this, "User data saved successfully.", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data.", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

private void loadFile() {
    // Load user data from a file using jfilechooser
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Load User Data");
    
    int userSelection = fileChooser.showOpenDialog(this);
    
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToLoad = fileChooser.getSelectedFile();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToLoad))) {
            nameField.setText(reader.readLine().split(": ")[1]);
            ageField.setText(reader.readLine().split(": ")[1]);
            weightField.setText(reader.readLine().split(": ")[1]);
            heightField.setText(reader.readLine().split(": ")[1]);
            genderComboBox.setSelectedItem(reader.readLine().split(": ")[1]);
            activityLevelComboBox.setSelectedItem(reader.readLine().split(": ")[1]);
            goalComboBox.setSelectedItem(reader.readLine().split(": ")[1]);
            waistField.setText(reader.readLine().split(": ")[1]);
            JOptionPane.showMessageDialog(this, "User data loaded successfully.", "Load Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Error loading user data.", "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    // Reset fields method
    private void resetFields() {
        nameField.setText("");
        ageField.setText("");
        weightField.setText("");
        heightField.setText("");
        waistField.setText("");
        bmiResultLabel.setText("BMI");
        calorieGoalLabel.setText("Daily calorie");
        bfpLabel.setText("Body Fat %");
    }
 // Method to generate and display diet and workout plan
 private void generateDietAndWorkoutPlan() {
    // Collect input values
    String name = nameField.getText();
    String age = ageField.getText();
    String weight = weightField.getText();
    String height = heightField.getText();
    String waist = waistField.getText();
    String gender = (String) genderComboBox.getSelectedItem();
    String goal = (String) goalComboBox.getSelectedItem();
    String activityLevel = (String) activityLevelComboBox.getSelectedItem();
    //  placeholder
    String plan = "Diet and Workout Plan for " + name + ":\n\n";
    plan += "Goal: " + goal + "\n";
    plan += "Activity Level: " + activityLevel + "\n\n";
    plan += "Diet:\n";
    
    // Generates diet plan based on BMI and fitness goal
    double bmi = calculateBMI();
    if (bmi < 18.5) {
        plan += "- Breakfast: Whole grain toast with avocado\n";
        plan += "- Snack: Almonds and dried fruits\n";
        plan += "- Lunch: Grilled chicken with quinoa and roasted vegetables\n";
        plan += "- Dinner: Salmon with brown rice and steamed broccoli\n";
        plan += "- Snack: Greek yogurt with berries\n";
    } else if (bmi >= 18.5 && bmi < 25) {
        plan += "- Breakfast: Greek yogurt with berries and nuts\n";
        plan += "- Snack: Apple slices with peanut butter\n";
        plan += "- Lunch: Turkey wrap with whole wheat tortilla\n";
        plan += "- Dinner: Grilled shrimp with quinoa and roasted asparagus\n";
        plan += "- Snack: Carrot sticks with hummus\n";
    } else {
        plan += "- Breakfast: Vegetable omelette with whole grain toast\n";
        plan += "- Snack: Edamame beans\n";
        plan += "- Lunch: Quinoa salad with mixed greens and grilled tofu\n";
        plan += "- Dinner: Baked salmon with sweet potato and steamed green beans\n";
        plan += "- Snack: Smoothie with spinach, banana, and almond milk\n";
    }
    
    plan += "\nWorkout:\n";
    
    // Generates workout plan based on fitness goal
    if (goal.equals("Weight Loss")) {
        plan += "- Monday: Cardio (30 minutes)\n";
        plan += "- Tuesday: Cardio (30 minutes)\n";
        plan += "- Wednesday: Strength Training (45 minutes)\n";
        plan += "- Thursday: Rest\n";
        plan += "- Friday: HIIT (20 minutes)\n";
            plan += "- Saturday: Strength Training (45 minutes)\n";
            plan += "- Sunday: Rest\n";
        
    } else if (goal.equals("Muscle Gain")) {
        plan += "- Monday: Chest and Triceps Workout\n";
        plan += "- Tuesday: Back and Biceps Workout\n";
        plan += "- Wednesday: Legs and Shoulders Workout\n";
        plan += "- Thursday: Chest and Triceps Workout\n";
        plan += "- Friday: Back and Biceps Workout\n";
        plan += "- Saturday: Legs and Shoulders Workout\n";
        plan += "- Sunday: Rest\n";
    } else {
        plan += "- Monday: Yoga (60 minutes)\n";
        plan += "- Tuesday: Cardio (30 minutes)\n";
        plan += "- Wednesday: Pilates (45 minutes)\n";
        plan += "- Thursday: Strength Training (45 minutes)\n";
        plan += "- Friday: Zumba (30 minutes)\n";
        plan += "- Saturday: HIIT (20 minutes)\n";
        plan += "- Sunday: Rest\n";
    }
    
    // Displays plan in an alert box
    JOptionPane.showMessageDialog(this, plan, "Diet and Workout Plan", JOptionPane.INFORMATION_MESSAGE);
}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonalFitnessCoach(backgroundImage, calculateBmiButton, calculateBodyFatButton);
            }
        });
    }
}
