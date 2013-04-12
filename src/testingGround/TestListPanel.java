package testingGround;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import twitter4j.Trend;
import utilities.ProgramStyle;

public class TestListPanel extends JPanel {
    
    private final Dimension prefSize = new Dimension(350, 50);
    
    private final int textSize = 20;

    public TestListPanel (
            final JList list,              // the list
            final Object value,            // value to display
            final int index,               // cell index
            final boolean isSelected,      // is the cell selected
            final boolean cellHasFocus)    // does the cell have focus
    {

        setPreferredSize(prefSize);
        
        if (isSelected) {
            setBackground(ProgramStyle.SELECT_COLOR);
        } else {
            setBackground(ProgramStyle.PANEL_COLOR);
        }
        
        Trend trend = ((Trend) value);
        
        JLabel trendLabel = new JLabel((index + 1) + ": " + trend.getName());
        trendLabel.setFont(ProgramStyle.getFont(textSize));
        trendLabel.setForeground(ProgramStyle.TEXT_COLOR);
        
        add(trendLabel);
    }
    
    public Dimension getPreferredSize() {
        return prefSize;
    }
}

//This is the block that will need to go into program style.



///*****************************************************************
//Accesses the renderer to paint the list items.
//
//@return ListCellRenderer used to paint the list items.
// *****************************************************************/
//public static ListCellRenderer getTestListRenderer () {
//    ProgramStyle temp = new ProgramStyle();
//    return temp.new TestListRenderer();
//}
//
///*****************************************************************
//The ListRenderer object that is used to paint items in a list
// *****************************************************************/
//class TestListRenderer extends JPanel implements ListCellRenderer {
//
//    /*****************************************************************
//    Required constructor to use to draw list items.
//     *****************************************************************/
//    @Override
//    public Component getListCellRendererComponent(
//            final JList list,              // the list
//            final Object value,            // value to display
//            final int index,               // cell index
//            final boolean isSelected,      // is the cell selected
//            final boolean cellHasFocus)    // does the cell have focus
//    {
//        return new TestListPanel(list, value, index, isSelected, cellHasFocus);
//    }
//}