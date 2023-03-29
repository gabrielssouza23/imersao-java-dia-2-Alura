import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class BestSeries {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes do IMDB
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json"; // usando endereço alternativo
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessa, (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeSeries = parser.parse(body);
        System.out.println("\u001b[1mTop séries do IMDB 🏆\u001b[0m");
        System.out.println();
        for (Map<String,String> filme : listaDeSeries) {
            System.out.print("\u001b[1mTítulo: \u001b[0m" + filme.get("title"));
            if(filme.get("rank").equals("1")){
                System.out.println("🥇");
            } else if(filme.get("rank").equals("2")){
                System.out.println("🥈");
            } else if(filme.get("rank").equals("3")){
                System.out.println("🥉");
            }
            System.out.println("\u001b[1mPoster:\u001b[0m " + filme.get("image"));
            System.out.println("\u001b[1mnota: \u001b[0m" + filme.get("imDbRating"));
            int imDbRating = (int) Math.round(Double.parseDouble(filme.get("imDbRating")));
            for (int i = 0; i <= 10 && i < imDbRating; i++) {
                System.out.print("🌟");
            }
            System.out.println();
            System.out.println();
        }
    }
}