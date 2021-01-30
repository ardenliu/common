package com.github.ardenliu.common.janusgraph;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLReader;
import org.apache.tinkerpop.gremlin.structure.io.graphml.GraphMLWriter;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import com.github.ardenliu.common.janusgraph.exception.ArdenJanusGraphException;

public class GraphMlLoader {
    private static final Logger logger = LogManager.getLogger(GraphMlLoader.class);

    public static String getGraphMlString(Graph graph) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            GraphMlLoader.writeGraph(outputStream, graph);

            return new String(outputStream.toByteArray());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ArdenJanusGraphException(e.getMessage(), e);
        }
    }

    public static void writeGraph(File file, Graph graph) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            GraphMlLoader.writeGraph(outputStream, graph);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ArdenJanusGraphException(e.getMessage(), e);
        }
    }
    
    public static void writeGraph(OutputStream outputStream, Graph graph) {
        try {
            GraphMLWriter graphMlWriter = GraphMLWriter.build().create();
            graphMlWriter.writeGraph(outputStream, graph);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ArdenJanusGraphException(e.getMessage(), e);
        }
    }

    public static Graph loadGraphFromFile(String filename) {
        Graph graph = TinkerGraph.open();
        graph.traversal().io(filename).read().iterate();
        return graph;
    }

    public static Graph loadGraphFromClassPath(String classpath) {
        try {
            Graph newGraph = TinkerGraph.open();

            GraphMLReader graphMLReader = GraphMLReader.build().create();
            InputStream inputStream = GraphMlLoader.class.getResourceAsStream(classpath);

            graphMLReader.readGraph(inputStream, newGraph);

            return newGraph;
        } catch (IOException e) {
            logger.error("Got IOException", e);
            throw new ArdenJanusGraphException(e.getMessage(), e);
        }
    }
}
