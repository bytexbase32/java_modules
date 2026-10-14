package org.example;

public class RendererErrImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String message) {
        String processedMessage = preProcessor.process(message);
        System.err.println(processedMessage);
    }
}
