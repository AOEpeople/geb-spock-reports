function update_filter() {
    var filter_input_element = document.getElementById('light-table-filter');

    if (document.getElementById('show-failed').checked) {
        filter_input_element.value = "FAILED";
        filter_input_element.dispatchEvent(new Event('input', {'target': filter_input_element}));
    } else if (document.getElementById('show-passed').checked) {
        filter_input_element.value = "PASSED";
        filter_input_element.dispatchEvent(new Event('input', {'target': filter_input_element}));
    } else if (document.getElementById('show-all').checked) {
        filter_input_element.value = "";
        filter_input_element.dispatchEvent(new Event('input', {'target': filter_input_element}));
    }
}

// table filter implementation from https://codepen.io/chriscoyier/pen/tIuBL
(function(document) {
    'use strict';

    var LightTableFilter = (function(Arr) {

        var _input;

        function _onInputEvent(e) {
            _input = e.target;

            var _value = _input.value.toUpperCase();
            document.getElementById('show-all').checked = _value === '';
            document.getElementById('show-failed').checked = _value === 'FAILED';
            document.getElementById('show-passed').checked = _value === 'PASSED';

            var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
            Arr.forEach.call(tables, function(table) {
                Arr.forEach.call(table.tBodies, function(tbody) {
                    Arr.forEach.call(tbody.rows, _filter);
                });
            });
        }

        function _filter(row) {
            var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
            row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
        }

        return {
            init: function() {
                var inputs = document.getElementsByClassName('light-table-filter');
                Arr.forEach.call(inputs, function(input) {
                    input.oninput = _onInputEvent;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function() {
        if (document.readyState === 'complete') {
            LightTableFilter.init();
        }
    });
})(document);
