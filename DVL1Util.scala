// package dvl1

// import org.apache.spark.SparkConf
// import org.apache.spark.sql.SparkSession
// import scala.util.Properties
// import java.io.{ IOException, FileOutputStream, FileInputStream, File }
// import java.util.zip.{ ZipEntry, ZipInputStream }

// object DVL1Utils {

//   def getSparkSession(appName: String): SparkSession = {

//     val conf = new SparkConf

//     conf.set("spark.master", Properties.envOrElse("SPARK_MASTER_URL", "spark://spark-master:7077"))
//     conf.set("spark.driver.host", Properties.envOrElse("SPARK_DRIVER_HOST", "local[*]"))
//     conf.set("spark.submit.deployMode", "client")
//     conf.set("spark.driver.bindAddress", "0.0.0.0")

//     conf.set("spark.app.name", appName)

//     SparkSession.builder.config(conf = conf).getOrCreate()
//   }
// }

// /**
//  * Created by anquegi on 04/06/15.
//  */
// object Unzip extends App {

//   val INPUT_ZIP_FILE: String = "src/main/resources/my-zip.zip";
//   val OUTPUT_FOLDER: String = "src/main/resources/my-zip";

//   def unZipIt(zipFile: String, outputFolder: String): Unit = {

//     val buffer = new Array[Byte](1024)

//     try {

//       //output directory
//       val folder = new File(OUTPUT_FOLDER);
//       if (!folder.exists()) {
//         folder.mkdir();
//       }

//       //zip file content
//       val zis: ZipInputStream = new ZipInputStream(new FileInputStream(zipFile));
//       //get the zipped file list entry
//       var ze: ZipEntry = zis.getNextEntry();

//       while (ze != null) {

//         val fileName = ze.getName();
//         val newFile = new File(outputFolder + File.separator + fileName);

//         System.out.println("file unzip : " + newFile.getAbsoluteFile());

//         //create folders
//         new File(newFile.getParent()).mkdirs();

//         val fos = new FileOutputStream(newFile);

//         var len: Int = zis.read(buffer);

//         while (len > 0) {

//           fos.write(buffer, 0, len)
//           len = zis.read(buffer)
//         }

//         fos.close()
//         ze = zis.getNextEntry()
//       }

//       zis.closeEntry()
//       zis.close()

//     } catch {
//       case e: IOException => println("exception caught: " + e.getMessage)
//     }

//   }

//   Unzip.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER)

// }