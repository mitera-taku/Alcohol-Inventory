function sortTable(n, isNumeric = false) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("productTable");  // テーブルを取得
    switching = true;
    dir = "asc";  // 昇順での並び替え

    while (switching) {
        switching = false;
        rows = table.rows;

        // 各行を比較して並び替え
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            
            let xValue = x.innerHTML.toLowerCase();
            let yValue = y.innerHTML.toLowerCase();
            
            // 数値の場合は数値として比較
            if (isNumeric) {
                xValue = parseFloat(xValue);
                yValue = parseFloat(yValue);
            }

            // 昇順で比較
            if (dir == "asc") {
                if (isNumeric ? (xValue > yValue) : (xValue > yValue)) {
                    shouldSwitch = true;
                    break;
                }
            // 降順で比較
            } else if (dir == "desc") {
                if (isNumeric ? (xValue < yValue) : (xValue < yValue)) {
                    shouldSwitch = true;
                    break;
                }
            }
        }

        // 行を入れ替える
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;
        } else {
            // ソートが完了していない場合、降順に切り替える
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}
