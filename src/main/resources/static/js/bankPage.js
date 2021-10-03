function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}


var bankApi = Vue.resource('/banks/{/id}');

Vue.component('bank-form', {
    props: ['banks', 'bankAttr'],
    data: function() {
        return {
            name: '',
            naturalPercent: '',
            nonNaturalPercent: '',
            id: ''
        }
    },
    watch: {
        bankAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.naturalPercent = newVal.naturalPercent;
            this.nonNaturalPercent = newVal.nonNaturalPercent;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Bank name" v-model="name" />' +
        '<input type="number" placeholder="Bank % for natural person" v-model="naturalPercent" />' +
        '<input type="number" placeholder="Bank % for NON-natural person" v-model="nonNaturalPercent" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var bank = { name: this.name, naturalPercent: this.naturalPercent, nonNaturalPercent: this.nonNaturalPercent  };

            if (this.id) {
                bankApi.update({id: this.id}, bank).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.bank, data.id);
                        this.banks.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                bankApi.save({}, bank).then(result =>
                    result.json().then(data => {
                        this.banks.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('bank-row', {
    props: ['bank', 'editMethod', 'banks'],
    template: '<div>' +
        '<i>{{ bank.id }} -- </i> {{ bank.name }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.bank);
        },
        del: function() {
            bankApi.remove({id: this.bank.id}).then(result => {
                if (result.ok) {
                    this.banks.splice(this.banks.indexOf(this.bank), 1)
                }
            })
        }
    }
});

Vue.component('banks-list', {
    props: ['banks'],
    data: function() {
        return {
            bank: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<bank-form :banks="banks" :bankAttr="bank" />' +
        '<bank-row v-for="bank in banks" :key="bank.id" :bank="bank" ' +
        ':editMethod="editMethod" :banks="banks" />' +
        '</div>',
    created: function() {
        bankApi.get().then(result =>
            result.json().then(data =>
                data.forEach(bank => this.banks.push(bank))
            )
        )
    },
    methods: {
        editMethod: function(bank) {
            this.bank = bank;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<banks-list :banks="banks" />',
    data: {
        banks: []
    }
});