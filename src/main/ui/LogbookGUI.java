package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Property;

// Based on https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class LogbookGUI extends JFrame implements ActionListener {
  JPanel mainPanel;
  JPanel viewPanel;
  JPanel createPanel;
  JLabel menuLabel;

  JTextField address;
  JTextField size;
  JList<Property> propertyJList;
  JPanel mainMenuButtonPanel;
  JButton mainMenuButton;
  private Logbook logbook;

  // MODIFIES: this
  // EFFECTS: initializes properties and runs gui app
  public LogbookGUI() {
    super("Property Maintenance Logbook");
    logbook = new Logbook(new ArrayList<>());

    mainMenuButton = new JButton("Back To Main Menu");
    mainMenuButton.setActionCommand("menu");
    mainMenuButton.addActionListener(this);
    mainMenuButtonPanel = new JPanel();
    mainMenuButtonPanel.add(mainMenuButton);
    mainMenuButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainMenuButtonPanel.setVisible(false);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(400, 600));
    ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
    setLayout(new FlowLayout());
    setupMenu();
    setupViewProperties();
    setUpCreateProperty();
    this.add(mainMenuButtonPanel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);
  }


  public void setupMenu() {
    mainPanel = new JPanel();
    try {
      BufferedImage myPicture = ImageIO.read(new File("./data/logbook.png"));
      JLabel image = new JLabel(new ImageIcon(myPicture));
      mainPanel.add(image);

    } catch(Exception e) {
    }
    menuLabel = new JLabel();

    JButton btn1 = new JButton("Add Property To Log Book");
    btn1.setActionCommand("createProperty");
    btn1.addActionListener(this);

    JButton btn3 = new JButton("View All Properties");
    btn3.setActionCommand("viewProperties");
    btn3.addActionListener(this);

    JButton btn4 = new JButton("Save Properties/Inspections");
    btn4.setActionCommand("save");
    btn4.addActionListener(this);

    JButton btn5 = new JButton("Load Properties/Inspections");
    btn5.setActionCommand("load");
    btn5.addActionListener(this);
    mainPanel.add(btn1);
    mainPanel.add(btn3);
    mainPanel.add(btn4);
    mainPanel.add(btn5);
    mainPanel.add(menuLabel);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    this.add(mainPanel);
  }

  private void setupViewProperties() {
    viewPanel = new JPanel();
    propertyJList = new JList<>();
    viewPanel.add(propertyJList);

    viewPanel.setVisible(false);

    viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
    viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(viewPanel);
  }

  private void setUpCreateProperty() {
    createPanel = new JPanel();

    JLabel labelAddress = new JLabel();
    labelAddress.setText("Address");

    JLabel labelSize = new JLabel();
    labelSize.setText("Size");

    address = new JTextField(20);
    size = new JTextField(20);

    createPanel.add(labelAddress);
    createPanel.add(address);
    createPanel.add(labelSize);
    createPanel.add(size);

    JButton saveButton = new JButton("Add Property");
    saveButton.setActionCommand("saveProperty");
    saveButton.addActionListener(this);

    createPanel.add(saveButton);

    createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));
    createPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    createPanel.setVisible(false);
    this.add(createPanel);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("createProperty")) {
      showCreateProperty();
    } else if (e.getActionCommand().equals("saveProperty")) {
      saveProperty();
    } else if (e.getActionCommand().equals("viewProperties")) {
      viewProperties();
    } else if (e.getActionCommand().equals("load")) {
      logbook.loadPropertyFromFile();
      menuLabel.setText("Loaded From File");
    } else if (e.getActionCommand().equals("save")) {
      logbook.savePropertyToFile();
      menuLabel.setText("Saved to File");
    } else if (e.getActionCommand().equals("menu")) {
      showMainMenu();
    }

  }

  private void showCreateProperty() {
    mainPanel.setVisible(false);
    createPanel.setVisible(true);
    mainMenuButtonPanel.setVisible(true);
  }

  private void saveProperty() {
    if (address.getText() != null && !address.getText().isEmpty() && size.getText() != null
        && !size.getText().isEmpty()) {
      Property p = new Property();
      p.setAddress(address.getText());
      p.setSize(Integer.parseInt(size.getText()));
      logbook.addPropertyToList(p);
      showMainMenu();
    }
  }

  private void viewProperties() {
    mainPanel.setVisible(false);

    DefaultListModel<Property> listModel = new DefaultListModel<>();
    for (Property p : logbook.getListProperty()) {
      listModel.addElement(p);
    }

    propertyJList.setModel(listModel);
    viewPanel.setVisible(true);
    mainMenuButtonPanel.setVisible(true);
  }

  private void showMainMenu() {
    address.setText("");
    size.setText("");
    mainPanel.setVisible(true);
    viewPanel.setVisible(false);
    createPanel.setVisible(false);
    mainMenuButtonPanel.setVisible(false);
    menuLabel.setText("");
  }
}
