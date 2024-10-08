function sortTable(n, isNumeric = false) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("productTable");  // テーブルを取得
    switching = true;
    dir = "asc";  // 初期状態は昇順

    // 他のヘッダーから既存のクラスを削除
    var headers = table.getElementsByTagName("th");
    for (i = 0; i < headers.length; i++) {
        headers[i].classList.remove("asc", "desc");
    }

    // 現在のヘッダーに昇順のクラスを追加
    headers[n].classList.add("asc");

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
                if (xValue > yValue) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (xValue < yValue) {
                    shouldSwitch = true;
                    break;
                }
            }
        }

        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;
        } else {
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
                headers[n].classList.remove("asc");  // 昇順クラスを削除
                headers[n].classList.add("desc");    // 降順クラスを追加
            }
        }
    }
}
