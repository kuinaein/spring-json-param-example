module.exports = [
  {
    path: '/',
    foo: {
      bar: 'ein',
      baz: ']]>&quot;\n\'</script>',
    },
  },
  {
    path: '/a/',
    foo: {
      bar: 'zwei',
      baz: ']]>&quot;\n\"\'</script>',
    },
  },
  {
    path: '/a/create',
    foo: {
      bar: 'drei',
      baz: ']]>&quot;\n\'</script>',
    },
  },
];
