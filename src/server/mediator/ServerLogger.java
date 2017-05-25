package server.mediator;

import com.google.gson.internal.StringMap;
import javafx.stage.FileChooser;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import server.Main;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

/**
 * Created by aykon on 24-May-17.
 */
public class ServerLogger extends Observable {

    private ArrayList<StringMap<Object>> actionsLog;

    public ServerLogger() {
        this.actionsLog = new ArrayList<>();
    }

    public synchronized void addAction(StringMap<Object> action) {
        actionsLog.add(action);
        super.setChanged();
        super.notifyObservers(this.actionsLog);
        System.out.println(actionsLog.toString());
    }

    public void saveLog() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Log");
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel file (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(Main.stage);
            if (file != null) {
                WritableWorkbook workbook = null;
                workbook = Workbook.createWorkbook(file);
                WritableSheet sheet = workbook.createSheet("Log", 0);

                CellView columnView1 = sheet.getColumnView(0);
                CellView columnView2 = sheet.getColumnView(1);
                CellView columnView3 = sheet.getColumnView(2);
                CellView columnView4 = sheet.getColumnView(3);
                columnView1.setAutosize(true);
                columnView2.setAutosize(true);
                columnView3.setAutosize(true);
                columnView4.setAutosize(true);
                sheet.setColumnView(0, columnView1);
                sheet.setColumnView(1, columnView2);
                sheet.setColumnView(2, columnView3);
                sheet.setColumnView(3, columnView4);


                WritableFont headFont = new WritableFont(WritableFont.ARIAL,
                        WritableFont.DEFAULT_POINT_SIZE,
                        WritableFont.BOLD,
                        false
                );

                WritableCellFormat head = new WritableCellFormat(headFont);
                head.setBackground(Colour.GRAY_50);
                head.setAlignment(Alignment.CENTRE);
                head.setVerticalAlignment(VerticalAlignment.CENTRE);

                WritableCellFormat redBackground = new WritableCellFormat();
                redBackground.setBackground(Colour.RED);

                Label headLabelIp = new Label(0, 0, "IP", head);
                Label headLabelAction = new Label(1, 0, "Action", head);
                Label headLabelLogged = new Label(2, 0, "Logged In", head);
                Label headLabelTime = new Label(3, 0, "Time", head);
                sheet.addCell(headLabelIp);
                sheet.addCell(headLabelAction);
                sheet.addCell(headLabelLogged);
                sheet.addCell(headLabelTime);

                Label labelIp;
                Label labelAction;
                Label labelLogged;
                Label labelTime;
                for (int i = 0; i < actionsLog.size(); i++) {
                    InetSocketAddress ip = (InetSocketAddress) actionsLog.get(i).get("IP");
                    Date date = (Date) actionsLog.get(i).get("Time");
                    if (actionsLog.get(i).get("Action").equals("Alert")) {
                        labelIp = new Label(0, i + 1, ip.toString(), redBackground);
                        labelAction = new Label(1, i + 1, (String) actionsLog.get(i).get("Action"), redBackground);
                        labelLogged = new Label(2, i + 1, ((boolean) actionsLog.get(i).get("LoggedIn")) ? "Yes" : "No", redBackground);
                        labelTime = new Label(3, i + 1, date.toString(), redBackground);
                    } else {
                        labelIp = new Label(0, i + 1, ip.toString());
                        labelAction = new Label(1, i + 1, (String) actionsLog.get(i).get("Action"));
                        labelLogged = new Label(2, i + 1, ((boolean) actionsLog.get(i).get("LoggedIn")) ? "Yes" : "No");
                        labelTime = new Label(3, i + 1, date.toString());
                    }
                    sheet.addCell(labelIp);
                    sheet.addCell(labelAction);
                    sheet.addCell(labelLogged);
                    sheet.addCell(labelTime);
                }
                workbook.write();
                workbook.close();
            }
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        }

    }
}