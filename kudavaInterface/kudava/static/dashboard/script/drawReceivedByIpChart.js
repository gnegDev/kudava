function drawReceivedByIpChart(packets) {
    const ipCount = {};
            
    packets.forEach(packet => {
        const ip = packet.dst_ip || 'unknown';
        ipCount[ip] = (ipCount[ip] || 0) + 1;
    });

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'IP');
    data.addColumn('number', 'Packets');
    data.addRows(Object.entries(ipCount).sort((a, b) => b[1] - a[1]));

    // Set chart options
    var options = {'title':'Packets received by',
                'backgroundColor': { fill:'transparent' },
                'width':300,
                'height':300,
                'pieHole':0.4};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('to-chart'));
    chart.draw(data, options);
}
