package ex03;

import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
import java.util.*;

public class Program {

    private static final String FILE_NAME = "files_urls.txt";

    public static void main(String[] args) throws Exception {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("Usage: java Program --threadsCount=N");
            return;
        }

        int threadsCount = Integer.parseInt(args[0].substring("--threadsCount=".length()));

        List<String> urls = Files.readAllLines(Paths.get(FILE_NAME));

        DownloadQueue queue = new DownloadQueue(urls);

        Thread[] workers = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            workers[i] = new Thread(new Downloader(queue), "Thread-" + (i + 1));
            workers[i].start();
        }

        for (Thread t : workers) {
            t.join();
        }
    }
}

class DownloadQueue {

    private final List<String> urls;
    private int index = 0;

    public DownloadQueue(List<String> urls) {
        this.urls = urls;
    }

    public synchronized DownloadTask nextTask() {
        if (index >= urls.size()) {
            return null;
        }

        DownloadTask task = new DownloadTask(index + 1, urls.get(index));
        index++;
        return task;
    }
}

class DownloadTask {
    int number;
    String url;

    public DownloadTask(int number, String url) {
        this.number = number;
        this.url = url;
    }
}

class Downloader implements Runnable {

    private final DownloadQueue queue;
    private final HttpClient client = HttpClient.newHttpClient();

    public Downloader(DownloadQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true) {

            DownloadTask task = queue.nextTask();

            if (task == null)
                break;

            System.out.println(Thread.currentThread().getName()
                    + " start download file number " + task.number);

            try {

                String filename = task.url.substring(task.url.lastIndexOf('/') + 1);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(task.url))
                        .build();

                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofFile(Paths.get(filename))
                );

            } catch (Exception e) {
                System.out.println("Failed: " + task.url);
            }

            System.out.println(Thread.currentThread().getName()
                    + " finish download file number " + task.number);
        }
    }
}