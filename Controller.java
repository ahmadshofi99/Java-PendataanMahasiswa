package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;

public class Controller {
    @FXML
    TextField tfNIM;
    @FXML
    TextField tfNama;
    @FXML
    TextField tfAlamat;
    @FXML
    Button btnSubmit;
    @FXML
    TableView tvDataMahasiswa;
    @FXML
    Button btnSave;
    @FXML
    Button btnLoad;

    @FXML
    public void onButtonClicked(ActionEvent e){
        if (e.getTarget().equals(btnSubmit)){
            //menambahkan data ketable TableView saat btnSubmit ditekan
            ObservableList<MahasiswaModel> data = tvDataMahasiswa.getItems();

            data.add(new MahasiswaModel(
                    tfNIM.getText(),
                    tfNama.getText(),
                    tfAlamat.getText()
            ));

            tfNIM.setText("");
            tfNama.setText("");
            tfAlamat.setText("");
        } else if(e.getTarget().equals(btnSave)){
            //menulis data yang ada di TableView ke file bernama data-mahasiswa.dat
            try{
                ObservableList<MahasiswaModel> data = tvDataMahasiswa.getItems();
                BufferedWriter writer = new BufferedWriter(new FileWriter("data-mahasiswa.dat"));
                for (int i = 0; i < data.size(); i++){
                    writer.write(String.valueOf(data.get(i).getNim()) + "," + String.valueOf(data.get(i).getNama()) + "," + String.valueOf(data.get(i).getAlamat()));
                    writer.newLine();
                }
                writer.close();

                //menampilkan dialog jika berhasil di simpan
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setContentText("Succes Save Data to File");
                alert.showAndWait();
            } catch (IOException ex){
                //menampilkan dialog jika error saat menyimpan file
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Error IO Exception: " + ex.getMessage());
                alert.showAndWait();
            }
        } else if(e.getTarget().equals(btnLoad)){
            //membaca data yang di file data-mahasiswa.dat ke TableView
            try{
                ObservableList<MahasiswaModel> data = tvDataMahasiswa.getItems();
                data.clear();
                BufferedReader reader = new BufferedReader(new FileReader("data-mahasiswa.dat"));

                String line;
                while ((line = reader.readLine()) != null){
                    String[] temp = line.split(",");
                    MahasiswaModel mahasiswaModel = new MahasiswaModel(temp[0], temp[1], temp[2]);
                    data.add(mahasiswaModel);
                }

                reader.close();

                //menampilkan dialog jika berhasil membaca file
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setContentText("Succes Load Data From Saved File");
                alert.showAndWait();
            } catch (IOException ex){
                //menampilkan dialog jika error saat membaca file
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Error IO Exception: " + ex.getMessage());
                alert.showAndWait();
            }
        }
    }
}
