package activity2;

import java.io.*;
import java.util.*;

// Represents a file or folder in our mock file system
class FileNode {
    String name;
    boolean isDirectory;
    List<FileNode> children;

    FileNode(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new ArrayList<>();
    }

    void addChild(FileNode child) {
        if (isDirectory) {
            children.add(child);
        }
    }
}

// Interface for handling found files
interface FileFoundListener {
    void onFileFound(String filePath, BufferedWriter writer) throws IOException;
}

// Searches for files with a specific extension
class FileSearcher {
    private FileFoundListener listener;

    FileSearcher(FileFoundListener listener) {
        this.listener = listener;
    }

    void searchFiles(FileNode node, String extension, String path, BufferedWriter writer) throws IOException {
        if (node.isDirectory) {
            for (FileNode child : node.children) {
                searchFiles(child, extension, path + "\\" + node.name, writer);
            }
        } else if (node.name.endsWith(extension)) {
            listener.onFileFound(path + "\\" + node.name, writer);
        }
    }
}

// Creates a fake file system for testing
class MockFileSystem {
    static FileNode createMockFileSystem() {
        FileNode root = new FileNode("Documents", true);
        FileNode subfolder = new FileNode("subfolder", true);
        
        root.addChild(new FileNode("notes.txt", false));
        root.addChild(new FileNode("homework.docx", false));
        subfolder.addChild(new FileNode("todo.txt", false));
        root.addChild(subfolder);

        return root;
    }
}

public class BangamuArrachchige_Activity2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter the directory path: ");
        String dirPath = scanner.nextLine();
        System.out.print("Enter file extension to search for: ");
        String extension = scanner.nextLine();

        FileNode root = MockFileSystem.createMockFileSystem();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("search_results.txt"))) {
            FileSearcher searcher = new FileSearcher((filePath, fileWriter) -> {
                System.out.println("File found: " + filePath);
                fileWriter.write("File found: " + filePath + "\n");
            });

            System.out.println("\nSearching...");
            searcher.searchFiles(root, extension, dirPath, writer);
            System.out.println("\nSearch completed. Results saved to search_results.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
