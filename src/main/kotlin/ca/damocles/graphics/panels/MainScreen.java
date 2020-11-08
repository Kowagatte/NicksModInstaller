/*
 * Created by JFormDesigner on Tue Nov 03 18:37:16 CST 2020
 */

package ca.damocles.graphics.panels;

import ca.damocles.Database;
import ca.damocles.MasterKt;
import ca.damocles.PlayerSettings;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout;

public class MainScreen extends JPanel {
    public MainScreen() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        String version = comboBox1.getItemAt(comboBox1.getSelectedIndex());
        String performanceMod = comboBox2.getItemAt(comboBox2.getSelectedIndex());
        List<String> selectedMods = new ArrayList<>();
        List<JCheckBoxMenuItem> listOfCheckBoxes = Arrays.asList(checkBoxMenuItem1, checkBoxMenuItem2, checkBoxMenuItem3, checkBoxMenuItem4, checkBoxMenuItem5, checkBoxMenuItem6, checkBoxMenuItem7, checkBoxMenuItem8);
        for(JCheckBoxMenuItem checkBox : listOfCheckBoxes){
            if(checkBox.isSelected()){
                selectedMods.add(checkBox.getText().toLowerCase());
            }
        }
        if(!performanceMod.equalsIgnoreCase(comboBox2.getItemAt(0))){
            MasterKt.install(new PlayerSettings(performanceMod, selectedMods), Database.INSTANCE.getVersion(version));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();
        label2 = new JLabel();
        comboBox2 = new JComboBox<>();
        label3 = new JLabel();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        checkBoxMenuItem2 = new JCheckBoxMenuItem();
        checkBoxMenuItem3 = new JCheckBoxMenuItem();
        checkBoxMenuItem4 = new JCheckBoxMenuItem();
        checkBoxMenuItem5 = new JCheckBoxMenuItem();
        checkBoxMenuItem6 = new JCheckBoxMenuItem();
        checkBoxMenuItem7 = new JCheckBoxMenuItem();
        checkBoxMenuItem8 = new JCheckBoxMenuItem();
        button1 = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (
        new javax. swing. border. EmptyBorder( 0, 0, 0, 0) , ""
        , javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
        , new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 )
        , java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
        ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );

        //---- label1 ----
        label1.setText("Nicks Mod Installer");

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "latest",
        }));

        //---- label2 ----
        label2.setText("Performance Mod");

        //---- comboBox2 ----
        comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
            "Please-Select",
            "OptiFabric",
            "Sodium"
        }));

        //---- label3 ----
        label3.setText("Optional Client Mods (Check to install)");

        //---- checkBoxMenuItem1 ----
        checkBoxMenuItem1.setText("Blur");

        //---- checkBoxMenuItem2 ----
        checkBoxMenuItem2.setText("Appleskin");

        //---- checkBoxMenuItem3 ----
        checkBoxMenuItem3.setText("ShulkerBoxToolTip");

        //---- checkBoxMenuItem4 ----
        checkBoxMenuItem4.setText("DarkLoadingScreen");

        //---- checkBoxMenuItem5 ----
        checkBoxMenuItem5.setText("InventoryProfiles");

        //---- checkBoxMenuItem6 ----
        checkBoxMenuItem6.setText("NBTTooltip");

        //---- checkBoxMenuItem7 ----
        checkBoxMenuItem7.setText("MiniHUD");

        //---- checkBoxMenuItem8 ----
        checkBoxMenuItem8.setText("DurabilityViewer");

        //---- button1 ----
        button1.setText("Install/Update");
        button1.addActionListener(e -> button1ActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkBoxMenuItem1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkBoxMenuItem2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkBoxMenuItem7, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkBoxMenuItem3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkBoxMenuItem4, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(checkBoxMenuItem5, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(checkBoxMenuItem6, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(checkBoxMenuItem8, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addComponent(button1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(checkBoxMenuItem2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkBoxMenuItem1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkBoxMenuItem7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(checkBoxMenuItem4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkBoxMenuItem3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(checkBoxMenuItem5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkBoxMenuItem6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(checkBoxMenuItem8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1)
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JComboBox<String> comboBox1;
    private JLabel label2;
    private JComboBox<String> comboBox2;
    private JLabel label3;
    private JCheckBoxMenuItem checkBoxMenuItem1;
    private JCheckBoxMenuItem checkBoxMenuItem2;
    private JCheckBoxMenuItem checkBoxMenuItem3;
    private JCheckBoxMenuItem checkBoxMenuItem4;
    private JCheckBoxMenuItem checkBoxMenuItem5;
    private JCheckBoxMenuItem checkBoxMenuItem6;
    private JCheckBoxMenuItem checkBoxMenuItem7;
    private JCheckBoxMenuItem checkBoxMenuItem8;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
