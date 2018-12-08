package com.reddylabs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/** */
public class SearchFancyNumber {
  private static final int RETURN_COUNT = 1000;
  private static String urlString =
          "https://www.godaddy.com/api/voice?isTollFree=False&isInRegions=US&count=" + RETURN_COUNT;
  private static int count;
  private static List<Integer> npas = null;

  public static void main(String[] args) throws IOException {
    npas = Arrays.stream(StateCodesCA.getNpa_array()).boxed().collect(Collectors.toList());
    int count = 1000;

    Set<String> fancy9999 = new TreeSet<>();
    Set<String> fancy99999 = new TreeSet<>();
    Set<String> severNines = new TreeSet<>();
    Set<String> sixNines = new TreeSet<>();
    Set<String> similarChars = new TreeSet<>();
    Set<String> six2nine = new TreeSet<>();

    int noOfRefresh = 10;
    urlString = urlString + "&npa=NPA_VALUE";
    for (int i = 0; i < noOfRefresh; i++) {
      System.out.println("Batch Job#" + i);
      for (Integer npa : npas) {
        System.out.println("\nFancy mining for area code: " + npa);

        try {
          URL url = new URL(urlString.replace("NPA_VALUE", npa.toString()));
          HttpURLConnection con = (HttpURLConnection) url.openConnection();
          con.setRequestMethod("GET");
          con.setDoOutput(true);
          con.setRequestProperty("Content-Type", "application/json");

          int status = con.getResponseCode();
          if (status != 200) break;

          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
          String inputLine;
          StringBuffer content = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
          }
          if (content.length() == 0) continue;

          Object object = JSONValue.parse(content.toString());
          JSONArray finalResult = (JSONArray) object;
          Iterator iterator = finalResult.iterator();
          while (iterator.hasNext()) {
            PhoneNumber phoneNumber =
                new Gson().fromJson(iterator.next().toString(), PhoneNumber.class);

                        if (phoneNumber.getTelephoneNumber().chars().filter(ch -> ch ==
             '9').count() >=5) {
                          sixNines.add(phoneNumber.getDisplayNumber());
                        }

                       if(phoneNumber.getTelephoneNumber().contains("999")){
                          severNines.add(phoneNumber.getDisplayNumber());
                          System.out.println(phoneNumber.getTelephoneNumber());

                        }
                          if(phoneNumber.getTelephoneNumber().contains("2494949")){
                              severNines.add(phoneNumber.getDisplayNumber());
                              System.out.println(phoneNumber.getTelephoneNumber());

                          }
                          if(phoneNumber.getTelephoneNumber().contains("2595959")){
                              severNines.add(phoneNumber.getDisplayNumber());
                              System.out.println(phoneNumber.getTelephoneNumber());

                          }
              System.out.println(phoneNumber.getTelephoneNumber());
            System.out.println(phoneNumber.getTelephoneNumber().substring(10,12));
              System.out.println(phoneNumber.getTelephoneNumber().substring(8,10));
              if(phoneNumber.getTelephoneNumber().substring(10,12).equals(phoneNumber.getTelephoneNumber().substring(8,10))){
                  severNines.add(phoneNumber.getDisplayNumber());
                  System.out.println(phoneNumber.getTelephoneNumber());
                  if(phoneNumber.getTelephoneNumber().substring(10,12).equals(phoneNumber.getTelephoneNumber().substring(6,8))){
                      fancy9999.add(phoneNumber.getDisplayNumber());
                      System.out.println(phoneNumber.getTelephoneNumber());
                  }

              }

              if(phoneNumber.getTelephoneNumber().substring(8).contains(phoneNumber.getTelephoneNumber().substring(5,8))){
                  severNines.add(phoneNumber.getDisplayNumber());
                  System.out.println(phoneNumber.getTelephoneNumber());
              }
            System.out.println(phoneNumber.getTelephoneNumber().substring(5,8));
              System.out.println(phoneNumber.getTelephoneNumber().substring(8));
              if(phoneNumber.getTelephoneNumber().substring(10,12).equals(phoneNumber.getTelephoneNumber().substring(8,10))){
                  severNines.add(phoneNumber.getDisplayNumber());
                  System.out.println(phoneNumber.getTelephoneNumber());

              }

                  for (int j = 9; j >= 4; j--) {
                      String regex = ".*[" + 9 + "]{" + j + ",}+.*";
                      if (phoneNumber.getTelephoneNumber().matches(regex)) {
                          sixNines.add(phoneNumber.getDisplayNumber());
                          System.out.println(phoneNumber.getTelephoneNumber());
                      }
                    String regex1 = ".*[" + 7 + "]{" + j + ",}+.*";
                    if (phoneNumber.getTelephoneNumber().matches(regex1)) {
                      sixNines.add(phoneNumber.getDisplayNumber());
                      System.out.println(phoneNumber.getTelephoneNumber());
                    }
                    String regex2 = ".*[" + 0 + "]{" + j + ",}+.*";
                    if (phoneNumber.getTelephoneNumber().matches(regex2)) {
                      sixNines.add(phoneNumber.getDisplayNumber());
                      System.out.println(phoneNumber.getTelephoneNumber());
                    }
                  }
            }


            in.close();
            con.disconnect();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }


        System.out.println(
                "\nStats :   "
                        + " 9999 count: "
                        + fancy9999.size()
                        + " 99999 count: "
                        + fancy99999.size()
                        + " Six nines: "
                        + sixNines.size()
                        + " Seven nines: "
                        + severNines.size()
                        + " similar chars: "
                        + similarChars.size());
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      FileWriter fancy9999_file = new FileWriter("fancy9999.json");
      fancy9999_file.write(gson.toJson(fancy9999));
      fancy9999_file.close();

      FileWriter fancy99999_file = new FileWriter("fancy99999.json");
      fancy99999_file.write(gson.toJson(fancy99999));
      fancy99999_file.close();

      FileWriter sixNines_file = new FileWriter("sixNines.json");
      sixNines_file.write(gson.toJson(sixNines));
      sixNines_file.close();

      FileWriter severNines_file = new FileWriter("severNines_file.json");
      severNines_file.write(gson.toJson(severNines));
      severNines_file.close();

      FileWriter similarChars_file = new FileWriter("similarChars.json");
      similarChars_file.write(gson.toJson(similarChars));
      similarChars_file.close();

      FileWriter six2nine_file = new FileWriter("six2nine.json");
      six2nine_file.write(gson.toJson(six2nine));

      six2nine_file.close();
    }
  }
