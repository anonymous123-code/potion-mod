import sys


def function_evaluate(lines: [str], own_line: str, tab_size: int):
    args = get_args(lines, tab_size)
    if len(args) == 0:
        return {"type": "void"}
    result = []
    for arg in args:
        if arg.type == "escaped_data":
            result.append(get_args(arg.lines, arg.tab_size))
        else:
            return {"type": "void"}
    if len(result) == 1:
        return result[0]
    else:
        return {
            "type": "arg_list",
            "list": result
        }


def function_store(lines: [str], own_line: str, tab_size: int):
    pass


def function_retrieve(lines: [str], own_line: str, tab_size: int):
    pass


def function_amount(lines: [str], own_line: str, tab_size: int):
    if len(own_line.split(' ')) > 1 and own_line.split(' ')[1].isnumeric():
        if not len(lines) == 0:
            return {"type": "void"}
        return {
            "type": "amount",
            "value": int(own_line.split(' ')[1])
        }
    result = 0
    for arg in get_args(lines, tab_size):
        if arg["type"] == "amount":
            result += arg["value"]
        else:
            return {"type": "void"}



def function_escape(lines: [str], own_line: str, tab_size: int):
    return {
        "type": "escaped_data",
        "lines": lines,
        "tab_size": tab_size
    }


def function_select(lines: [str], own_line: str, tab_size: int):
    pass


def function_negativity(lines: [str], own_line: str, tab_size: int):
    pass


def get_args(lines: [str], tab_size: int):
    return [parse_file_recursion(line_list, tab_size) for line_list in split_arguments(lines, tab_size)]


def split_arguments(lines: [str], tab_size: int) -> [[str]]:
    result: [[str]] = []
    for line in lines:
        indent_char = '\t' if tab_size == -1 else ' ' * tab_size
        if not line.startswith(indent_char):
            assert not line.startswith(' ') or line.startswith('\t')
            result.append([line])
        else:
            result[len(result) - 1].append(line)
    return result


functions = {"evaluate": function_evaluate, "store": function_store, "amount": function_amount,
             "retrieve": function_retrieve, "escape": function_escape, "select": function_select,
             "negativity": function_negativity}


def parse_file(lines: [str]):
    tab_size = -2
    lines_without_newlines = [line.rstrip("\n") for line in lines]
    print(lines_without_newlines)

    for line in lines:
        if line.startswith("//"):
            continue
        if line.startswith(" ") and tab_size == 0:
            tab_size = len(line) - len(line.lstrip(' '))
        elif line.startswith("\t") and tab_size == 0:
            tab_size = -1

        indentation_level = ((len(line) - len(line.lstrip(' '))) / tab_size) if tab_size > 0 else len(line) - len(
            line.lstrip('\t'))

        assert (indentation_level == 0 if tab_size == -2 else indentation_level != 0)
        if tab_size == -1 or tab_size > 0:
            break
        tab_size = 0

    parse_file_recursion(lines, tab_size)


def parse_file_recursion(lines: [str], tab_size: int):
    line = lines[0]
    indentation_level = ((len(line) - len(line.lstrip(' '))) / tab_size) if tab_size > 0 else len(line) - len(
        line.lstrip('\t'))
    assert indentation_level == 0
    if line.split(" ")[0].lower() in functions.keys():
        return functions[line.split(" ")[0].lower()](line, [
            line.removeprefix(' ' * tab_size if tab_size > 0 else '\t') for line in lines
        ], tab_size)
    else:
        print("ERROR: unknown keyword:" + line)


# a
#   b
#   b
#      c
#      c
#   b

if __name__ == "__main__":
    argv = sys.argv[1:]
    with open(argv[0], "r") as f:
        parse_file(f.readlines())
