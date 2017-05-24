package server.mediator;

import com.google.gson.internal.StringMap;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;

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
            WritableWorkbook workbook = null;
            workbook = Workbook.createWorkbook(new File("server-log.xls"));
            WritableSheet sheet = workbook.createSheet("Log", 0);
            Label headLabelIp = new Label(0, 0, "IP");
            Label headLabelAction = new Label(1, 0, "Action");
            Label headLabelLogged = new Label(2, 0, "Logged In");
            Label headLabelTime = new Label(3, 0, "Time");
            sheet.addCell(headLabelIp);
            sheet.addCell(headLabelAction);
            sheet.addCell(headLabelLogged);
            sheet.addCell(headLabelTime);

            WritableCellFormat redBackground = new WritableCellFormat();
            redBackground.setBackground(Colour.RED);

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
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        }



        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Log");
        System.out.println(pic.getId());
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
                        null), "png", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
    }
}
