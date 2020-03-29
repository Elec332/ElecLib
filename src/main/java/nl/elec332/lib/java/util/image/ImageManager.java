package nl.elec332.lib.java.util.image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Created by Elec332 on 18-3-2020
 */
public class ImageManager<ARRAY> {

    public ImageManager(Type<ARRAY> type) {
        this.type = type;
        this.lock = new ReentrantLock(true);
    }

    public static final Type<byte[]> TYPE_BYTE_BW = new Type<>(BufferedImage.TYPE_BYTE_GRAY, byte[]::new, image -> ((DataBufferByte) image.getRaster().getDataBuffer()).getData(), (pixels, newProcPixels, oldWeight, newWeight) -> {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (byte) (oldWeight * pixels[i] + newProcPixels[i] * newWeight);
        }
    });
    public static final Type<int[]> TYPE_INT_RGB = new Type<>(BufferedImage.TYPE_INT_RGB, int[]::new, image -> ((DataBufferInt) image.getRaster().getDataBuffer()).getData(), (pixels, newProcPixels, oldWeight, newWeight) -> {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (int) (oldWeight * pixels[i] + newProcPixels[i] * newWeight);
        }
    });

    protected final Type<ARRAY> type;
    protected final ReentrantLock lock;
    private BufferedImage image;
    private int length;
    private ARRAY pixelBytes;
    private int width, height;

    public void setImageSize(int width, int height) {
        image = new BufferedImage(width, height, type.type);
        pixelBytes = type.rawDataGetter.apply(image);
        this.length = width * height;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getPixels() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPixelData(Consumer<ARRAY> processor) {
        lock.lock();
        processor.accept(getPreBuffer());
        lock.unlock();
    }

    protected ARRAY getPreBuffer() {
        return getPixelBytes();
    }

    protected ARRAY getPixelBytes() {
        return this.pixelBytes;
    }

    protected final ARRAY createArray() {
        return createArray(this.length);
    }

    protected final ARRAY createArray(int length) {
        return type.arrayCreator.apply(length);
    }

    public static class Type<ARR> implements DataSetter<ARR> {

        private Type(int type, IntFunction<ARR> arrayCreator, Function<BufferedImage, ARR> rawDataGetter, DataSetter<ARR> dataSetter) {
            this.type = type;
            this.arrayCreator = arrayCreator;
            this.rawDataGetter = rawDataGetter;
            this.dataSetter = dataSetter;
        }

        private final int type;
        private final IntFunction<ARR> arrayCreator;
        private final Function<BufferedImage, ARR> rawDataGetter;
        private final DataSetter<ARR> dataSetter;

        @Override
        public void setData(ARR pixels, ARR newProcPixels, double oldWeight, double newWeight) {
            this.dataSetter.setData(pixels, newProcPixels, oldWeight, newWeight);
        }

    }

    private interface DataSetter<ARR> {

        void setData(ARR pixels, ARR newProcPixels, double oldWeight, double newWeight);

    }

}

