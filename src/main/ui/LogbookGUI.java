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

import model.Inspection;
import model.Property;

// Represents the controller for responding to all actions taken by user in gui application
// Based on https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
public class LogbookGUI extends JFrame implements ActionListener {
    JPanel mainPanel;
    JPanel viewPanel;
    JPanel createPanel;
    JPanel createInspectionPanel;
    JLabel menuLabel;

    JTextField address;
    JTextField size;
    JTextField addressInspection;
    JTextField date;
    JTextField plumbing;
    JTextField drywall;
    JTextField addedNotes;
    JList<Property> propertyJList;
    JPanel mainMenuButtonPanel;
    JButton mainMenuButton;
    private Logbook logbook;

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
        setUpAddInspectionToProperty();
        this.add(mainMenuButtonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: Creates main menu panel and initializes main menu buttons with labels
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void setupMenu() {
        mainPanel = new JPanel();
        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/logbook.png"));
            JLabel image = new JLabel(new ImageIcon(myPicture));
            mainPanel.add(image);

        } catch (Exception e) {
            System.out.println("Failed to load image");
        }
        menuLabel = new JLabel();

        JButton btn1 = new JButton("Add Property To Log Book");
        btn1.setActionCommand("createProperty");
        btn1.addActionListener(this);

        JButton btn2 = new JButton("Add Inspection To Property");
        btn2.setActionCommand("createInspection");
        btn2.addActionListener(this);

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
        mainPanel.add(btn2);
        mainPanel.add(btn3);
        mainPanel.add(btn4);
        mainPanel.add(btn5);
        mainPanel.add(menuLabel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that displays the current properties
    private void setupViewProperties() {
        viewPanel = new JPanel();
        propertyJList = new JList<>();
        viewPanel.add(propertyJList);

        viewPanel.setVisible(false);

        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(viewPanel);
    }

    // MODIFIES: this
    // EFFECTS: Adds the user given property into the LogbookGUI object to be displayed
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

    // MODIFIES: this
    // EFFECTS: Adds the panel for creating inspection
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void setUpAddInspectionToProperty() {
        createInspectionPanel = new JPanel();
        date = new JTextField(8);
        addressInspection = new JTextField(20);
        plumbing = new JTextField(30);
        drywall = new JTextField(30);
        addedNotes = new JTextField(30);
        createInspectionPanel.add(new JLabel("Property Address"));
        createInspectionPanel.add(addressInspection);
        createInspectionPanel.add(new JLabel("Date (MMDDYYYY)"));
        createInspectionPanel.add(date);
        createInspectionPanel.add(new JLabel("Condition of Plumbing"));
        createInspectionPanel.add(plumbing);
        createInspectionPanel.add(new JLabel("Condition of Drywall"));
        createInspectionPanel.add(drywall);
        createInspectionPanel.add(new JLabel("Additional Notes"));
        createInspectionPanel.add(addedNotes);
        JButton saveButton = new JButton("Add Inspection");
        saveButton.setActionCommand("saveInspection");
        saveButton.addActionListener(this);
        createInspectionPanel.add(saveButton);
        createInspectionPanel.setLayout(new BoxLayout(createInspectionPanel, BoxLayout.Y_AXIS));
        createInspectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createInspectionPanel.setVisible(false);
        this.add(createInspectionPanel);
    }

    // EFFECTS: calls the given methods when a certain button is clicked on
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("createProperty")) {
            showCreateProperty();
        } else if (e.getActionCommand().equals("createInspection")) {
            mainPanel.setVisible(false);
            createInspectionPanel.setVisible(true);
            mainMenuButtonPanel.setVisible(true);
        } else if (e.getActionCommand().equals("saveInspection")) {
            saveInspection();
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

    // EFFECTS: shows "Add Property to Log Book" panel
    private void showCreateProperty() {
        mainPanel.setVisible(false);
        createPanel.setVisible(true);
        mainMenuButtonPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: save inspection to property object
    private void saveInspection() {
        if (addressInspection.getText() != null && !addressInspection.getText().isEmpty() && date.getText() != null
                && !date.getText().isEmpty() && plumbing.getText() != null && !plumbing.getText()
                .isEmpty()
                && drywall.getText() != null && !drywall.getText().isEmpty()
                && addedNotes.getText() != null
                && !addedNotes.getText().isEmpty()) {
            Property p = logbook.findProperty(addressInspection.getText());

            if (p != null) {
                Inspection inspection = new Inspection(date.getText(), plumbing.getText(),
                        drywall.getText(), addedNotes.getText());
                p.addInspection(inspection);
            }
            showMainMenu();
        }
    }


    // MODIFIES: this
    // EFFECTS: saves property to logbook object
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

    // EFFECTS: displays the "View All Properties" panel
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

    // EFFECTS: displays the main menu panel
    private void showMainMenu() {
        address.setText("");
        size.setText("");
        date.setText("");
        plumbing.setText("");
        drywall.setText("");
        addedNotes.setText("");
        address.setText("");
        mainPanel.setVisible(true);
        viewPanel.setVisible(false);
        createPanel.setVisible(false);
        mainMenuButtonPanel.setVisible(false);
        createInspectionPanel.setVisible(false);
        menuLabel.setText("");
    }
}
