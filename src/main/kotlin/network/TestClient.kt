package network

import java.nio.channels.AsynchronousServerSocketChannel
import java.nio.channels.AsynchronousSocketChannel
import org.bouncycastle.crypto.tls.ConnectionEnd.client
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit
import org.bouncycastle.crypto.tls.ConnectionEnd.client






fun main() {

    val client = AsynchronousSocketChannel.open()

    val hostAddress = InetSocketAddress("35.192.83.92", 8333)
    val future = client.connect(hostAddress)

    println("Connect Result: ${future.get()}.")
    // val worker = future.get(10, TimeUnit.SECONDS) // Use timeout


    val magic = 0xf9beb4d9L

    val ints = intArrayOf(
        0xf9, 0xbe, 0xb4, 0xd9, 0x76, 0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x64, 0x00, 0x00, 0x00, 0x35, 0x8d, 0x49, 0x32, 0x62, 0xea, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x11, 0xb2, 0xd0, 0x50, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x3b, 0x2e, 0xb3, 0x5d, 0x8c, 0xe6, 0x17, 0x65, 0x0f, 0x2f, 0x53, 0x61, 0x74, 0x6f, 0x73, 0x68,
        0x69, 0x3a, 0x30, 0x2e, 0x37, 0x2e, 0x32, 0x2f, 0xc0, 0x3e, 0x03, 0x00)
    val bytes = ints.foldIndexed(ByteArray(ints.size)) { i, a, v -> a.apply { set(i, v.toByte()) } }

    val message = ByteBuffer.wrap(bytes)
    val get = client.write(message).get()

    print("Result: $get")
    client.close()


//    ByteBuffer.wrap("version".toByteArray())
//    client.write()

//    String [] messages = new String [] {"Time goes fast.", "What now?", "Bye."};
//
//    for (int i = 0; i < messages.length; i++) {
//        byte [] message = new String(messages [i]).getBytes();
//        ByteBuffer buffer = ByteBuffer.wrap(message);
//        Future result = client.write(buffer);

}
