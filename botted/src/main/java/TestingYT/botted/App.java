package TestingYT.botted;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		System.out.println("Programma Avviato =)");
		SaveYouTubeMP3ToFile("https://www.youtube.com/watch?v=WWRy1zT-CjI");
	}
	
	/**
	 * @note New Method For Downloading Music.
	 * @param URL_YOUTUBE_VIDEO
	 */
	
	public static void SaveYouTubeMP3ToFile(String URL_YOUTUBE_VIDEO) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select Dir To Save mp3");
		chooser.setCurrentDirectory(new File(System.getenv("USERNAME") + File.separator + "Deskstop"));
		int retrival = chooser.showOpenDialog(chooser);
		String url = URL_YOUTUBE_VIDEO;
		String video_id = url.replace("https://www.youtube.com/watch?v=", "");
		String diocane = video_id.replace(" ", "");
		String url_complete = "http://cdn.audioham.net/api/v2/download?videoId=" + diocane + "&itag=140&name=" + chooser.getSelectedFile() + ".mp3";
		URL url_2;
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				try {
					System.out.println("Richiesta Inviata All' API");
					url_2 = new URL(url_complete);
					InputStream in;
					ByteArrayOutputStream by_arr;
					int max_data = 8224;
					try {
						in = url_2.openStream();
						by_arr = new ByteArrayOutputStream(max_data);
						System.out.println("Copiando I File Data Dall API...");
						int length = -1;
						byte[] buffer = new byte[max_data];
						while ((length = in.read(buffer)) > -1) {
							by_arr.write(buffer, 0, length);
						}
						by_arr.close();
						in.close();
						try (FileOutputStream fw = new FileOutputStream(chooser.getSelectedFile() + ".mp3")) {
							System.out.println("Copiando I Byte Sul File Locale...");
							fw.write(by_arr.toByteArray());
							System.out.println("File Scaricato in " + chooser.getSelectedFile().getPath().toString() + ".mp3");
						} catch (Exception ex1) {
							System.out.println("Download Dei Byte Del File Non Riuscito");
						}
						System.out.println("File Scaricato Correttamente");
					} catch (IOException e) {
						System.out.println("Download Non Riuscito");
					}
				} catch (MalformedURLException e) {
					System.out.println("La Sintassi Della URL è Errata!");
				}

			} catch (Exception ex) {
				System.out.println("Impossibile Inizializzare Il File");
			}
		}
	}

	/**
	 * @note Olded Method
	 * @param YOUTUBE_LINKED
	 * @param TITLE_OF_SONG
	 */
	
	public static void DonwloadFileFromYT(String YOUTUBE_LINKED, String TITLE_OF_SONG) {
		String url = YOUTUBE_LINKED;
		String video_id = url.replace("https://www.youtube.com/watch?v=", "");
		String diocane = video_id.replace(" ", "");
		System.out.println("Video URL = " + url);
		System.out.println("Video ID = " + diocane);
		String url_complete = "http://cdn.audioham.net/api/v2/download?videoId=" + diocane + "&itag=140&name="
				+ TITLE_OF_SONG + ".mp3";
		System.out.println("URL Complete Was Set To " + url_complete);
		System.out.println("Start Opening Connection..");
		URL url_2;
		try {
			url_2 = new URL(url_complete);
			InputStream in;
			try {
				in = url_2.openStream();
				FileOutputStream fos = new FileOutputStream(new File(TITLE_OF_SONG + ".mp3"));
				System.out.println("Copiando I File Data Dall API...");
				int length = -1;
				byte[] buffer = new byte[8024];
				while ((length = in.read(buffer)) > -1) {
					fos.write(buffer, 0, length);
				}
				fos.close();
				in.close();
				System.out.println("File Scaricato Correttamente");
			} catch (IOException e) {
				System.out.println("Download Non Riuscito");
			}
		} catch (MalformedURLException e) {
			System.out.println("La Sintassi Della URL è Errata!");
		}
	}
}
