from nfstream import NFStreamer

streamer = NFStreamer(source="out.pcap")

for flow in streamer:
    print(flow)