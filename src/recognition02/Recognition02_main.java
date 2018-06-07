package recognition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition02_main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("j16005");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("img/fruitbowl.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .imagesFile(imagesStream)
		  .imagesFilename("fruitbowl.jpg")
		  .threshold((float) 0.6)
		  .owners(Arrays.asList("IBM"))
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node=null;
		try{
			node = mapper.readTree(result.toString());
		}catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String class0=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(0).get("class").asText();
		System.out.println("class :"+class0);
		double score0=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(0).get("score").asDouble();
		System.out.println("score :"+score0);
		String class1=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(1).get("class").asText();
		System.out.println("class :"+class1);
		double score1=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(1).get("score").asDouble();
		System.out.println("score :"+score1);
		String class2=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(2).get("class").asText();
		System.out.println("class :"+class2);
		double score2=node.get("images").get(0).get("classifiers").get(0).
				get("classes").get(2).get("score").asDouble();
		System.out.println("score :"+score2);
		MySQL mysql = new MySQL();
		mysql.updateImage(class0,score0,class1,score1,class2,score2);
	}

}
