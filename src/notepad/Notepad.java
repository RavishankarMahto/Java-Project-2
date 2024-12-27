package notepad;

import javax.swing.*; // for swing component
import javax.swing.filechooser.*;
import java.awt.*;   // awt = abstract window toolkit (for awt component like image)
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;

    Notepad() {
        setTitle("notepad");  // title

        // Set an icon for the JFrame
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icon/notepad.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.white);

        JMenu file = new JMenu("File");
        file.setFont(new Font("Arial", Font.ITALIC, 15));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem opendoc = new JMenuItem("Open");
        opendoc.addActionListener(this);
        opendoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem savedoc = new JMenuItem("Save");
        savedoc.addActionListener(this);
        savedoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem saveasdoc = new JMenuItem("Save as");
        saveasdoc.addActionListener(this);
        saveasdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

        JMenuItem printdoc = new JMenuItem("Print");
        printdoc.addActionListener(this);
        printdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem closetabdoc = new JMenuItem("Close tab");
        closetabdoc.addActionListener(this);
        closetabdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

        JMenuItem exitdoc = new JMenuItem("Exit");
        exitdoc.addActionListener(this);
        exitdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));

        file.add(newdoc);
        file.add(opendoc);
        file.add(savedoc);
        file.add(saveasdoc);
        file.add(printdoc);
        file.add(closetabdoc);
        file.add(exitdoc);

        menubar.add(file);  // add file in menubar


        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("Arial", Font.ITALIC, 15));

        JMenuItem undodoc = new JMenuItem("Undo");
        undodoc.addActionListener(this);
        undodoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        JMenuItem cutdoc = new JMenuItem("Cut");
        cutdoc.addActionListener(this);
        cutdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem copydoc = new JMenuItem("Copy");
        copydoc.addActionListener(this);
        copydoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem pastedoc = new JMenuItem("Paste");
        pastedoc.addActionListener(this);
        pastedoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem deletedoc = new JMenuItem("Delete");
        deletedoc.addActionListener(this);
        deletedoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));

        JMenuItem selectalldoc = new JMenuItem("Select All");
        selectalldoc.addActionListener(this);
        selectalldoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        JMenuItem finddoc = new JMenuItem("Find");
        finddoc.addActionListener(this);
        finddoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

        JMenuItem replacedoc = new JMenuItem("Replace");
        replacedoc.addActionListener(this);
        replacedoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        edit.add(undodoc);
        edit.add(cutdoc);
        edit.add(copydoc);
        edit.add(pastedoc);
        edit.add(deletedoc);
        edit.add(selectalldoc);
        edit.add(finddoc);
        edit.add(replacedoc);

        menubar.add(edit);  // add edit in menubar



        JMenu view = new JMenu("View");
        view.setFont(new Font("Arial", Font.ITALIC, 15));

        JMenuItem zoomdoc = new JMenuItem("Zoom");
        zoomdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        JMenuItem statusbardoc = new JMenuItem("Status bar");
        statusbardoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem wordwrapdoc = new JMenuItem("Word wrap");
        wordwrapdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        view.add(zoomdoc);
        view.add(statusbardoc);
        view.add(wordwrapdoc);

        menubar.add(view);


        JMenu help = new JMenu("Help");  // create menu bar
        help.setFont(new Font("Arial", Font.ITALIC, 15));

        JMenuItem helpdoc = new JMenuItem("About"); // create menu item
        helpdoc.addActionListener(this);
        zoomdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        help.add(helpdoc); // add in help item

        menubar.add(help);  // add in menubar


        setJMenuBar(menubar);


        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);     // one line completed automatically break into next line
        area.setWrapStyleWord(true);  // whole word move to next line

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        // set JFrame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);    // use for visible
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("New")) {
            area.setText(" ");
        }
        else if(ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");

            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }
        else if(ae.getActionCommand().equals("Print")) {
            try{
                area.print();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
           text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All")){
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About")) {

        }
    }

    public static void main(String[] args) {
        new Notepad();
    }

}
