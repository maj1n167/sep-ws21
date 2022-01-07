/*package ude.sep.client.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RestaurantbesitzerStatistikController extends ConnectionController  {
    private Bestellungen bestellungen;
    private int userId;

    public int anzahlBestellungen;
    public int restAnzahlBestellungen;



     //1 Teil
            public <K, V extends Comparable<V>> V maxUsingStreamAndLambda(Map<K, V> map) {

                Optional<Entry<K, V>> maxEntry = map.entrySet()
                        .stream()
                        .max((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue()
                                .compareTo(e2.getValue())
                        );

                return maxEntry.get().getValue();
            }

             public <K, V extends Comparable<V>> V minUsingStreamAndLambda(Map<K, V> map) {
                Optional<Entry<K, V>> minEntry = map.entrySet()
                .stream()
                .min((Entry<K, V> e1, Entry<K, V> e2) -> e1.getValue()
                        .compareTo(e2.getValue())
                );
                return minEntry.get().getValue();
    }

     // 2teil
        private void sortBewertungChange () {

                Map<String,Person> sortedNewMap = map.entrySet().stream().sorted((e1,e2)->
                            e1.getValue().getLocation().compareTo(e2.getValue().getLocation()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));
            sortedNewMap.forEach((key,val)->{
                System.out.println(key+ " = "+ val.toString());
            });

        }


        @FXML
        public void backButton ()throws IOException {
            changeScene("Startseite.fxml");
        }
    }
    
 */
