class Util:
    @staticmethod
    def get_dict_value(dict_val, key_name):
        return dict_val[key_name] if dict_val.keys().__contains__(key_name) else None
