package TestingYT.botted;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Inserisci Il Link Della Canzone Da Scaricare:");
		Scanner in = new Scanner(System.in);
		String link_da_scaricare = in.nextLine();
		System.out.println("Inserisci Il Nome Che Vuoi Dare Al File");
		Scanner in2 = new Scanner(System.in);
		String nome_song = in2.nextLine();
		DonwloadFileFromYT(link_da_scaricare, nome_song);
	}

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